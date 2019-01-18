package service;

import data.IDataSource;
import domain.CPRNumber;
import networking.adapters.message_queue.notification.INotification;

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
}
