package networking.adapters.rest;

import data.MockDatabase;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import networking.adapters.message_queue.observer.ObserverRabbitMQ;
import service.TokenService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Sebastian s144071
 */
@ApplicationPath("/")
public class RestApplication extends Application {

    public static TokenService tokenService = new TokenService(
            MockDatabase.getInstance(),
            new NotificationRabbitMQ()
    );

    public RestApplication() {
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
