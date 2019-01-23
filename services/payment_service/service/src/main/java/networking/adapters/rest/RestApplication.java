package networking.adapters.rest;

import access_bank.AccessBank;
import data.InMemoryDataSource;
import networking.adapters.message_queue.MQObserver;
import networking.notifications.RabbitMQNotificationService;
import service.PaymentService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Sebastian (s144071)
 */
@ApplicationPath("/")
public class RestApplication extends Application {
    public static PaymentService paymentService = new PaymentService(
            InMemoryDataSource.getInstance(),
            new RabbitMQNotificationService(),
            new AccessBank()
    );

    public RestApplication() {
        super();

        // Instantiate `MQObserver`.
        try {
            MQObserver.init();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
