package data;

import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import domain.Token;

import java.util.List;

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

    List<Token> getTokensByCustomer(CPRNumber cprNumber);

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param token
     * @return
     */
    Token putToken(Token token);

    void deleteToken(String tokenId);
}
