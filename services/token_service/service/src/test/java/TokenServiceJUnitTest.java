import com.google.gson.Gson;
import data.IDataSource;
import data.MockDatabase;
import domain.CPRNumber;
import exceptions.InvalidCprException;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import org.junit.Test;
import service.TokenService;

import static org.junit.Assert.assertEquals;

public class TokenServiceJUnitTest {

    private IDataSource data;
    private TokenService tokenService;
    private Gson gson;

    /**
     * @author Esben Løvendal Kruse (s172986)
     */
    public TokenServiceJUnitTest() {
        this.data = MockDatabase.getInstance();
        this.tokenService = new TokenService(data, new NotificationRabbitMQ());
        this.gson = new Gson();
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
     * @throws InvalidCprException
     */
    @Test
    public void testToJsonConversion() throws InvalidCprException {
        TokenInfo tokenFromPayment = new TokenInfo("DK11111111", 100, "123");
        String tokenInfoJson = this.gson.toJson(tokenFromPayment);

        TokenInfo tokenFromJson = this.gson.fromJson(tokenInfoJson, TokenInfo.class);
        TokenInfoVerified tokenInfoVerified =
                new TokenInfoVerified(tokenFromJson.getMerchantId(),
                        tokenFromJson.getPaymentAmount(),
                        tokenFromJson.getTokenId(),
                        new CPRNumber("270271-2467"));

        assertEquals(tokenFromPayment.getMerchantId(), tokenInfoVerified.getMerchantId());
        assertEquals(tokenFromPayment.getPaymentAmount(), tokenInfoVerified.getPaymentAmount());
        assertEquals(tokenFromPayment.getTokenId(), tokenInfoVerified.getTokenId());
        assertEquals(new CPRNumber("270271-2467").toString(), tokenInfoVerified.getCprNumber());
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @throws InvalidCprException
     */
    @Test
    public void testFromJsonConversion() throws InvalidCprException {
        TokenInfoVerified tokenInfoVerified =
                new TokenInfoVerified("DK11111111", 100, "123", new CPRNumber("270271-2467"));
        String toJson = this.gson.toJson(tokenInfoVerified);

        TokenInfoVerified tokenFromJson = this.gson.fromJson(toJson, TokenInfoVerified.class);

        assertEquals(tokenInfoVerified.getMerchantId(), tokenInfoVerified.getMerchantId());
        assertEquals(tokenInfoVerified.getPaymentAmount(), tokenInfoVerified.getPaymentAmount());
        assertEquals(tokenInfoVerified.getTokenId(), tokenInfoVerified.getTokenId());
        assertEquals(tokenInfoVerified.getCprNumber(), tokenFromJson.getCprNumber());
    }
}
