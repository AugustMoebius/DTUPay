package service;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import data.IDataSource;
import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import domain.Token;
import exceptions.InvalidCprException;
import exceptions.TokenAlreadyUsedException;
import exceptions.TooManyUnusedTokensException;
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
import service.exceptions.InvalidTokenCountException;
import service.exceptions.TokenGenerationFailedException;


public class TokenService {

    private static final String IMAGE_FORMAT = "png";

    private IDataSource data;
    private INotification iNotification;

    /**
     * @author Esben (s172986)
     * @param data
     */
    public TokenService(IDataSource data, INotification iNotification) {
        this.data = data;
        this.iNotification = iNotification;
    }

    /**
     * @author Ebbe (s125015)
     * @param tokenId
     * @return CPRNumber
     */
    public CPRNumber getCPRNumber(String tokenId) throws TokenNotFoundException {
        Token token = data.getToken(tokenId);
        return token.getCprNumber();
    }

    /**
     * @author Esben (s172986)
     * @param tokenInfo
     */
    public void handleTokenInfo(TokenInfo tokenInfo) throws TokenAlreadyUsedException, TokenNotFoundException {
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
     * This method handles the generation of tokens after verifying the request data.
     * @author Esben (s172986)
     * @param tokenRequest
     * @return token generation response
     * @throws TokenGenerationFailedException
     */
    public TokenGeneratedResponse handleTokenGenerateRequests(TokenRequest tokenRequest) throws TokenGenerationFailedException, TooManyUnusedTokensException {
        // Extract and validate CPR
        CPRNumber cpr;
        try {
            cpr = new CPRNumber(tokenRequest.getCprNumber());
        } catch (InvalidCprException e) {
            throw new TokenGenerationFailedException(e.getMessage());
        }

        // Extract and validate no. of tokens requested
        int tokenCount = tokenRequest.getNumberOfTokens();

        if (tokenCount < 1 || tokenCount > 5) {
            throw new InvalidTokenCountException();
        }

        int numberOfUnusedTokens = getNumberOfUnusedTokens(getTokensByCustomer(cpr));
        if (numberOfUnusedTokens > 1) {
            throw new TooManyUnusedTokensException();
        }

        List<Token> tokens = this.generateTokens(cpr, tokenRequest.getNumberOfTokens());
        List<TokenBarcodePair> tokenBarcodePairs = new ArrayList<>();

        for (Token t : tokens) {
            TokenBarcodePair tokenBarcodePair = new TokenBarcodePair(t.getId(), "/barcode/" + t.getBarcodeFileName());
            tokenBarcodePairs.add(tokenBarcodePair);
        }

        TokenGeneratedResponse tokenGeneratedResponse = new TokenGeneratedResponse(tokenBarcodePairs);

        return tokenGeneratedResponse;
    }

    /**
     * @author Esben (s172986)
     * @param id
     * @return token get response
     * @throws InvalidCprException
     */
    public TokenGetResponse handleTokenGetRequests(String id) throws TokenNotFoundException {
        Token token = data.getToken(id);
        TokenGetResponse tokenGetResponse = new TokenGetResponse(token.getId(), token.getCprNumber(), token.getBarcodeFileName(), token.isUsed());

        return tokenGetResponse;
    }

    /**
     * @author Esben (s172986)
     * @param numberOfTokens
     * @param cprNumber
     * @return a list of tokens
     */
    public List<Token> generateTokens(CPRNumber cprNumber, int numberOfTokens) {
        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < numberOfTokens; i++) {
            tokens.add(generateToken(cprNumber));
        }

        return tokens;
    }

    /**
     * @author Esben (s172986)
     * @param cprNumber
     * @return token
     */
    private Token generateToken(CPRNumber cprNumber) {
        Token token = new Token(cprNumber);

        this.data.putToken(token);
        token = storeBarcode(token);

        return token;
    }

    /**
     * @suthor August (s144461)
     * @param token
     * @return a barcode
     */
    private BitMatrix generateQRCode(Token token) {
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

    /**
     * @author Sebastian (s144071)
     * @param token
     * @return token
     */
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
     * @author August (s144461)
     * @param tokenId
     * @return token
     */
    public Token getTokenById(String tokenId) throws TokenNotFoundException {
        return data.getToken(tokenId);
    }

    /**
     * @author August (s144461)
     * @param tokenId
     */
    public void deleteToken(String tokenId) {
        // Deletes database reference
        data.deleteToken(tokenId);
    }

    /**
     * @author Sebastian (s144071)
     * @param cprNumber
     * @return a list of tokens
     */
    private List<Token> getTokensByCustomer(CPRNumber cprNumber) {
        return this.data.getTokensByCustomer(cprNumber);
    }

    /**
     * @author Sebastian (s144071)
     * @param tokens
     * @return amount of unused tokens
     */
    private int getNumberOfUnusedTokens(List<Token> tokens) {
        int unusedTokens = 0;

        for (Token t : tokens) {
            if (!t.isUsed()) {
                unusedTokens += 1;
            }
        }

        return unusedTokens;
    }
}
