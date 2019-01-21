package service;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import data.IDataSource;
import domain.CPRNumber;
import domain.Token;
import exceptions.InvalidCprException;
import exceptions.TokenAlreadyUsedException;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.INotification;
import networking.adapters.rest.requests.TokenRequest;
import networking.adapters.rest.responses.TokenBarcodePair;
import networking.adapters.rest.responses.TokenGeneratedResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import networking.adapters.rest.responses.TokenGetResponse;



public class TokenService {

    private static final String IMAGE_FORMAT = "png";

    private IDataSource data;
    private INotification iNotification;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param data
     */
    public TokenService(IDataSource data, INotification iNotification) {
        this.data = data;
        this.iNotification = iNotification;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @param tokenId
     * @return CPRNumber
     */
    public CPRNumber getCPRNumber(String tokenId) {
        CPRNumber cprNumber = data.getToken(tokenId).getCprNumber();
        return cprNumber;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenInfo
     */
    public void handleTokenInfo(TokenInfo tokenInfo) throws TokenAlreadyUsedException {
        CPRNumber cprNumber = getCPRNumber(tokenInfo.getTokenId());

        // Check if token is already used
        Token token = data.getToken(tokenInfo.getTokenId());
        if (token.isUsed()){
            throw new TokenAlreadyUsedException("Token with ID:" + tokenInfo.getTokenId() +" has already been used.");
        }
        // Set token used and write to DB
        token.setUsed(true);
        data.putToken(token);

        //Build verified response
        TokenInfoVerified tokenInfoVerified =
                new TokenInfoVerified(tokenInfo.getMerchantId(),
                        tokenInfo.getPaymentAmount(),
                        tokenInfo.getTokenId(),
                        cprNumber);

        try {
            iNotification.addMessage(tokenInfoVerified);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenRequest
     * @return
     * @throws InvalidCprException
     */
    public TokenGeneratedResponse handleTokenGenerateRequests(TokenRequest tokenRequest) throws InvalidCprException {
        List<Token> tokens = this.generateTokens(new CPRNumber(tokenRequest.getCprNumber()), tokenRequest.getNumberOfTokens());
        List<TokenBarcodePair> tokenBarcodePairs = new ArrayList<>();

        for (Token t : tokens) {
            TokenBarcodePair tokenBarcodePair = new TokenBarcodePair(t.getId(), "/barcode/" + t.getBarcodeFileName());
            tokenBarcodePairs.add(tokenBarcodePair);
        }

        TokenGeneratedResponse tokenGeneratedResponse = new TokenGeneratedResponse(tokenBarcodePairs);

        return tokenGeneratedResponse;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param id
     * @return
     * @throws InvalidCprException
     */
    public TokenGetResponse handleTokenGetRequests(String id) {
        Token token = data.getToken(id);
        TokenGetResponse tokenGetResponse = new TokenGetResponse(token.getId(), token.getCprNumber(), token.getBarcodeFileName(), token.isUsed());

        return tokenGetResponse;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param numberOfTokens
     * @param cprNumber
     * @return
     */
    public List<Token> generateTokens(CPRNumber cprNumber, int numberOfTokens) {
        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < numberOfTokens; i++) {
            tokens.add(generateToken(cprNumber));
        }

        return tokens;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     * @return
     */
    private Token generateToken(CPRNumber cprNumber) {
        Token token = new Token(cprNumber);

        this.data.putToken(token);
        token = storeBarcode(token);

        return token;
    }

    private BitMatrix generateQRCode(Token token){
        int height = 400;
        int width = 400;

        BitMatrix matrix = new BitMatrix(width, height);
        try {
            matrix = new QRCodeWriter().encode(token.getId(), BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    private Token storeBarcode(Token token) {
        try {
            String filePath = token.getId() + "." + IMAGE_FORMAT;
            MatrixToImageWriter.writeToStream(generateQRCode(token), IMAGE_FORMAT,
                    new FileOutputStream(new File(filePath)));

            token.setBarcodeFileName(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * @author August
     * @param tokenId
     * @return token
     */
    public Token getTokenById(String tokenId) {
        return data.getToken(tokenId);
    }

    /**
     * @author August
     * @param tokenId
     */
    public void deleteToken(String tokenId) {
        // TODO: Delete barcode img file
        // Deletes database reference
        data.deleteToken(tokenId);
    }
}
