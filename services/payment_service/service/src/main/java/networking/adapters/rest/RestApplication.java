package networking.adapters.rest;

import data.InMemoryDataSource;
import networking.adapters.message_queue.MQObserver;
import networking.notifications.RabbitMQNotificationService;
import service.PaymentService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {
    public static PaymentService paymentService = new PaymentService(
            InMemoryDataSource.getInstance(),
            new RabbitMQNotificationService()
    );

    public RestApplication() {
        super();

        // Instantiate `MQObserver`.
        MQObserver.init();
    }
}
