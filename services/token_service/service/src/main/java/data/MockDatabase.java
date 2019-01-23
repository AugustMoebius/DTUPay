package data;

import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import domain.Token;
import exceptions.InvalidCprException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MockDatabase implements IDataSource {
    private static final MockDatabase mockDatabase = new MockDatabase();
    private HashMap<String, Token> allTokens;

    /**
     * @author Esben (s172986)
     */
    private MockDatabase() {
        allTokens = new HashMap<>();

        // Adding all tokens to the database
        Token token = null;
        Token token2 = null;
        try {
            token = new Token("123", new CPRNumber("270271-2467"));
            token2 = new Token("234", new CPRNumber("270271-1234"));
        } catch (InvalidCprException e) {
            e.printStackTrace();
        }

        allTokens.put(token.getId(), token);
        allTokens.put(token2.getId(), token2);
    }

    /**
     * @author Ebbe (s125015)
     * @return instance of mock database
     */
    public static MockDatabase getInstance() {
        return mockDatabase;
    }

    /**
     * @author Esben (s172986)
     * @param tokenId
     * @return token
     */
    public Token getToken(String tokenId) throws TokenNotFoundException {
        Token token = allTokens.get(tokenId);
        if(token == null){
            throw new TokenNotFoundException("Token with id: " + tokenId +" was not found");
        }
        return token;
    }

    /**
     * @author Sebastian (s144071)
     * @param cprNumber
     * @return list of tokens
     */
    @Override
    public List<Token> getTokensByCustomer(CPRNumber cprNumber) {
        List<Token> tokensForCustomer = new ArrayList<>();

        for(Map.Entry<String, Token> element: allTokens.entrySet()) {
            if (element.getValue().getCprNumber().toString().equals(cprNumber.toString())) {
                tokensForCustomer.add(element.getValue());
            }
        }

        return tokensForCustomer;
    }

    /**
     * @author Esben (s172986)
     * @param token
     * @return token
     */
    @Override
    public Token putToken(Token token) {
        allTokens.put(token.getId(), token);
        return token;
    }

    /**
     * @author August (s144461)
     * @param tokenId
     */
    @Override
    public void deleteToken(String tokenId) {
        allTokens.remove(tokenId);
    }
}
