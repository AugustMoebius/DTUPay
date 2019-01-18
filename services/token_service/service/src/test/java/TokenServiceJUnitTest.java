import com.sun.media.sound.InvalidFormatException;
import data.IDataSource;
import data.MockDatabase;
import domain.CPRNumber;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import networking.adapters.message_queue.observer.ObserverRabbitMQ;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

    /*
    @Test
    public void testReceiveRabbitMQ() throws IOException, TimeoutException, InterruptedException {
        ObserverRabbitMQ observerRabbitMQ = new ObserverRabbitMQ();
        observerRabbitMQ.listen();
        NotificationRabbitMQ notificationRabbitMQ = new NotificationRabbitMQ();
        notificationRabbitMQ.sendMessage();
        String test = "";
    }
    */
}
