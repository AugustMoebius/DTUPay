package networking.adapters.message_queue.notification;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import networking.adapters.message_queue.HostMessageQueue;
import networking.adapters.message_queue.domain.TokenInfoVerified;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Ebbe s125015
 */
public class NotificationRabbitMQ implements INotification {

    //private final static String QUEUE_NAME = "token";
    private final static String QUEUE_NAME = "payment_verified";
    private final static String EXCHANGE_NAME = "payment_exchange";

    /**
     * @author Ebbe s125015
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    @Override
    public void addMessage(TokenInfoVerified message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HostMessageQueue.BASE.url);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String routingKeyMerchant = "payment.signed";

            // Serialize message
            Gson gson = new Gson();
            String tokenInfoVerfiedJson = gson.toJson(message);

            channel.basicPublish(EXCHANGE_NAME,
                    routingKeyMerchant,
                    null,
                    tokenInfoVerfiedJson.getBytes());

            System.out.println(" [signed] Sent '" + tokenInfoVerfiedJson + "'");
        }
    }
}
