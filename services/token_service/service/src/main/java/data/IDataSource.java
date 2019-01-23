package data;

import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import domain.Token;

import java.util.List;

public interface IDataSource {

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenID
     * @return token
     */
    Token getToken(String tokenID) throws TokenNotFoundException;

    /**
     * @author Sebastian
     * @param cprNumber
     * @return list of tokens
     */
    List<Token> getTokensByCustomer(CPRNumber cprNumber);

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param token
     * @return token
     */
    Token putToken(Token token);

    /**
     * @author August
     * @param tokenId
     */
    void deleteToken(String tokenId);
}
