package data;

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
     * @author Esben Løvendal Kruse (s172986)
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
     * @author Ebbe Berthold (s125015)
     * @return instance of mock database
     */
    public static MockDatabase getInstance() {
        return mockDatabase;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenId
     * @return token
     */
    public Token getToken(String tokenId) {
        Token token = allTokens.get(tokenId);
        return token;
    }

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
     * @author Esben Løvendal Kruse (s172986)
     * @param token
     * @return
     */
    @Override
    public Token putToken(Token token) {
        allTokens.put(token.getId(), token);
        return token;
    }

    /**
     * @author August
     * @param tokenId
     */
    @Override
    public void deleteToken(String tokenId) {
        allTokens.remove(tokenId);
    }
}
