package networking.adapters.rest;

import data.InMemoryDataSource;
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
}
