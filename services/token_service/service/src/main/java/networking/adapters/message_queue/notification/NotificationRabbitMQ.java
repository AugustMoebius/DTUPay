package networking.adapters.message_queue.notification;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import networking.WebEndpoints;
import networking.adapters.message_queue.domain.TokenInfoVerified;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NotificationRabbitMQ implements INotification {

    private final static String QUEUE_NAME = "token";

    /**
     * @author Ebbe Berthold (s125015)
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    @Override
    public void addMessage(TokenInfoVerified message) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(WebEndpoints.BASE.url);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Gson gson = new Gson();
            String tokenInfoVerfiedJson = gson.toJson(message);

            channel.basicPublish("", QUEUE_NAME, null, tokenInfoVerfiedJson.getBytes());
            System.out.println(" [x] Sent '" + tokenInfoVerfiedJson + "'");
        }
    }
}
