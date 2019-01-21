package networking.adapters.rest;

import data.InMemoryDataSource;
import management.MerchantManagement;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import service.MerchantService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {
    public static MerchantService merchantService = new MerchantService(
            InMemoryDataSource.getInstance(),
            new MerchantManagement(),
            new NotificationRabbitMQ()
    );
}
