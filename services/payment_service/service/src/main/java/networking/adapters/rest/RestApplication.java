package networking.adapters.rest;

import data.InMemoryDataSource;
import networking.adapters.message_queue.MQObserver;
import networking.notifications.RabbitMQNotificationService;
import service.PaymentService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationPath("/")
public class RestApplication extends Application {
    public static PaymentService paymentService = new PaymentService(
            InMemoryDataSource.getInstance(),
            new RabbitMQNotificationService()
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
