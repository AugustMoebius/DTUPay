package data;

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
    Token getToken(String tokenID);

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param token
     * @return
     */
    Token putToken(Token token);

}
