package networking.notifications;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.exceptions.MessagePublishException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


/**
 * @author Sebastian, August
 */
public class RabbitMQNotificationService implements INotificationService {
    private final static String QUEUE_NAME = "payment_initialized";
    private final static String EXCHANGE_NAME = "payment_exchange";
    private final static String HOST_URI = "rabbitmq-container";

    /**
     * This method handles a payment request and publishes a payment initialised.
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

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String routingKey = "payment.initialized";
            byte[] message = gson.toJson(req).getBytes(StandardCharsets.UTF_8);
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message);
            System.out.println(" [Initialized] Sent '" + req.getMerchantId() + "'");
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
            throw new MessagePublishException(e.getLocalizedMessage());
        }
    }
}
