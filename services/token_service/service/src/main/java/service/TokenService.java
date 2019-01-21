package service;

import data.IDataSource;
import domain.CPRNumber;
import domain.Token;
import exceptions.InvalidCprException;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.INotification;
import networking.adapters.rest.requests.TokenRequest;
import networking.adapters.rest.response.TokenResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static networking.adapters.rest.RestApplication.tokenService;

public class TokenService {

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
    public void handleTokenInfo(TokenInfo tokenInfo) {
        CPRNumber cprNumber = this.getCPRNumber(tokenInfo.getTokenId());

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
    public TokenResponse handleTokenRequests(TokenRequest tokenRequest) throws InvalidCprException {
        List<Token> tokens = tokenService.generateTokens(new CPRNumber(tokenRequest.getCprNumber()), tokenRequest.getNumberOfTokens());

        List<String> tokenIds = new ArrayList<>();
        List<String> barcodePaths = new ArrayList<>();

        for (Token t : tokens) {
            tokenIds.add(t.getId());
            barcodePaths.add(t.getBarcodePath());
        }

        TokenResponse tokenResponse = new TokenResponse(tokenIds, barcodePaths);

        return tokenResponse;
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

        return token;
    }
}
