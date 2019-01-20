package service;

import data.IDataSource;
import domain.CPRNumber;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.INotification;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
        CPRNumber cprNumber = data.getToken(tokenId).getCPRNumber();
        return cprNumber;
    }

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
}
