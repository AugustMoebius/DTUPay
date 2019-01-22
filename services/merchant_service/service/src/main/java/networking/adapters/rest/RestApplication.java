package networking.adapters.rest;

import data.InMemoryDataSource;
import management.MerchantManagement;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import networking.adapters.message_queue.observer.ObserverRabbitMQ;
import service.MerchantService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ApplicationPath("/")
public class RestApplication extends Application {
    public static MerchantService merchantService = new MerchantService(
            InMemoryDataSource.getInstance(),
            new MerchantManagement(),
            new NotificationRabbitMQ()
    );

    public RestApplication(){
        super();

        try {
            ObserverRabbitMQ.getInstance().listen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
