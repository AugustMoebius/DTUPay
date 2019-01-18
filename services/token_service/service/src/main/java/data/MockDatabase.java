package data;

import com.sun.media.sound.InvalidFormatException;
import domain.CPRNumber;
import domain.Token;

import java.util.HashMap;

public final class MockDatabase implements IDataSource {
    private static final MockDatabase mockDatabase = new MockDatabase();
    private HashMap<String, Token> allTokens;

    /**
     * @author Esben Løvendal Kruse (s172986)
     */
    private MockDatabase() {
        allTokens = new HashMap<String, Token>();

        // Adding all tokens to the database
        Token token = null;
        try {
            token = new Token("123", new CPRNumber("270271-2467"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        allTokens.put(token.getId(), token);
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
}
