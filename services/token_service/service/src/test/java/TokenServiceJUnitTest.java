import com.sun.media.sound.InvalidFormatException;
import data_access_layer.IDataAccessLayer;
import data_access_layer.MockDatabase;
import domain.CPRNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenServiceJUnitTest {

    private IDataAccessLayer dal;
    private TokenService tokenService;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @throws InvalidFormatException
     */
    public TokenServiceJUnitTest() throws InvalidFormatException {
        this.dal = new MockDatabase();
        this.tokenService = new TokenService(dal);
    }

    /**
     * @author Ebbe Berthold (s125015)
     */
    @Test
    public void testSavedToken() {
        CPRNumber cprNumber = tokenService.getCPRNumber("123");
        assertEquals("270271-2467", cprNumber.toString());
    }
}
