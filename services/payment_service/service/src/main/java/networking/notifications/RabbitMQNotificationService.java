package networking.notifications;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.exceptions.MessagePublishException;
import networking.notifications.exceptions.NotificationException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @author Sebastian, August
 */
public class RabbitMQNotificationService implements INotificationService {
    private final static String QUEUE_NAME = "payment_initialized";
    private final static String HOST_URI = "02267-munich.compute.dtu.dk";

    /**
     * @author August
     * @param req
     * @throws MessagePublishException
     */
    @Override
    public void publishPaymentInitialized(PaymentRequest req) throws MessagePublishException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_URI);
        Gson gson = new Gson();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // Serialize payment request
            String reqJson = gson.toJson(req);
            channel.basicPublish("", QUEUE_NAME, null, reqJson.getBytes());
        } catch (TimeoutException | IOException e) {
            throw new MessagePublishException(e.getLocalizedMessage());
        }
    }
}
