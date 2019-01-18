package networking.adapters.message_queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : August, Sebastian
 */
public class MQObserver {
  private static MQObserver instance;
  private final static String QUEUE_NAME = "token-verification";
  private final static String HOST_URI = "02267-munich.compute.dtu.dk";

  public static MQObserver getInstance() throws IOException, TimeoutException {
    if (MQObserver.instance == null) {
      MQObserver.instance = new MQObserver();
    }

    return MQObserver.instance;
  }

  public static void init() throws IOException, TimeoutException {
    MQObserver.instance = new MQObserver();
  }

  private MQObserver() throws IOException, TimeoutException {
    // Configure MQObserver with channel, etc.
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST_URI);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [x] Received '" + message + "'");
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
  }
}
