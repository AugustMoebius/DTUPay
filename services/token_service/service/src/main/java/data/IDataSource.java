package data;

import data.exceptions.TokenNotFoundException;
import domain.Token;

/**
 *
 */
public interface IDataSource {

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenID
     * @return
     */
    Token getToken(String tokenID) throws TokenNotFoundException;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param token
     * @return
     */
    Token putToken(Token token);


    void deleteToken(String tokenId);
}
