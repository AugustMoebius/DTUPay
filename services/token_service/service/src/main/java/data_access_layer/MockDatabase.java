package data_access_layer;

import com.sun.media.sound.InvalidFormatException;
import domain.CPRNumber;
import domain.Token;

import java.util.HashMap;

public class MockDatabase implements IDataAccessLayer {
    private HashMap<String, Token> allTokens;

    /**
     * @author Ebbe Berthold (s125015)
     * @throws InvalidFormatException
     */
    public MockDatabase() throws InvalidFormatException {
        allTokens = new HashMap<String, Token>();

        // Adding all tokens to the database
        Token token = new Token("123", new CPRNumber("270271-2467"));

        allTokens.put(token.getId(), token);
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
