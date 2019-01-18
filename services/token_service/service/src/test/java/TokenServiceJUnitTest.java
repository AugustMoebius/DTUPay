import com.google.gson.Gson;
import com.sun.media.sound.InvalidFormatException;
import data.IDataSource;
import data.MockDatabase;
import domain.CPRNumber;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import org.junit.Test;
import service.TokenService;

import static org.junit.Assert.assertEquals;

public class TokenServiceJUnitTest {

    private IDataSource data;
    private TokenService tokenService;

    /**
     * @author Esben Løvendal Kruse (s172986)
     */
    public TokenServiceJUnitTest() {
        this.data = MockDatabase.getInstance();
        this.tokenService = new TokenService(data);
    }

    /**
     * @author Ebbe Berthold (s125015)
     */
    @Test
    public void testSavedToken() {
        CPRNumber cprNumber = tokenService.getCPRNumber("123");
        assertEquals("270271-2467", cprNumber.toString());
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @throws InvalidFormatException
     */
    @Test
    public void testGsonConvertion() throws InvalidFormatException {
        TokenInfo tokenFromPayment = new TokenInfo("DK11111111", 100, "123");
        Gson gson = new Gson();
        String tokenInfoJson = gson.toJson(tokenFromPayment);

        TokenInfo tokenFromJson = gson.fromJson(tokenInfoJson, TokenInfo.class);
        TokenInfoVerified tokenInfoVerified =
                new TokenInfoVerified(tokenFromJson.getMerchantId(),
                        tokenFromJson.getPaymentAmount(),
                        tokenFromJson.getTokenId(),
                        new CPRNumber("270271-2467"));

        assertEquals(tokenFromPayment.getMerchantId(), tokenInfoVerified.getMerchantId());
        assertEquals(tokenFromPayment.getPaymentAmount(), tokenInfoVerified.getPaymentAmount());
        assertEquals(tokenFromPayment.getTokenId(), tokenInfoVerified.getTokenId());
        assertEquals(new CPRNumber("270271-2467").toString(), tokenInfoVerified.getCprNumber().toString());
    }
}
