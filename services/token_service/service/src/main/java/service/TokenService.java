package service;

import data.IDataSource;
import domain.CPRNumber;

public class TokenService {

    private IDataSource data;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param data
     */
    public TokenService(IDataSource data) {
        this.data = data;
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
