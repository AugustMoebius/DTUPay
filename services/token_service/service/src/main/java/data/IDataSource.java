package data;

import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import domain.Token;

import java.util.List;

public interface IDataSource {

    /**
     * @author Esben (s172986)
     * @param tokenID
     * @return token
     */
    Token getToken(String tokenID) throws TokenNotFoundException;

    /**
     * @author Sebastian (s144071)
     * @param cprNumber
     * @return a list of tokens
     */
    List<Token> getTokensByCustomer(CPRNumber cprNumber);

    /**
     * @author Esben (s172986)
     * @param token
     * @return one token
     */
    Token putToken(Token token);

    /**
     * @author August (s144461)
     * @param tokenId
     */
    void deleteToken(String tokenId);
}
