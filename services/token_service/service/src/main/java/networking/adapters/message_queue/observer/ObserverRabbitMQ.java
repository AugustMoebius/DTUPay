package networking.adapters.message_queue.observer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import networking.WebEndpoints;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ObserverRabbitMQ implements IObserver {

    private final static String QUEUE_NAME = "token";

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @throws IOException
     * @throws TimeoutException
     */
    public void listen() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(WebEndpoints.BASE.url);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
