package networking.adapters.message_queue;

import access_bank.AccessBank;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import data.InMemoryDataSource;
import networking.notifications.RabbitMQNotificationService;
import service.PaymentService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author : August, Sebastian
 */
public class MQObserver {
  private static MQObserver instance;
  private final static String QUEUE_NAME = "payment_verified";
  private final static String HOST_URI = "02267-munich.compute.dtu.dk";
  private static PaymentService paymentService = new PaymentService(
    InMemoryDataSource.getInstance(),
    new RabbitMQNotificationService(),
    new AccessBank());

  /**
   * @author Sebastian
   * @return MQObserver
   * @throws IOException
   * @throws TimeoutException
   */
  public static MQObserver getInstance() throws IOException, TimeoutException {
    if (MQObserver.instance == null) {
      MQObserver.instance = new MQObserver();
    }

    return MQObserver.instance;
  }

  public static void init() throws IOException, TimeoutException {
    MQObserver.instance = new MQObserver();
  }

  /**
   * @author Sebastian
   * @throws IOException
   * @throws TimeoutException
   */
  private MQObserver() throws IOException, TimeoutException {
    // Configure MQObserver with channel, etc.
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST_URI);
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String body = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println("Received message on queue '" + QUEUE_NAME + "': " + body);

      PaymentVerifiedRequest paymentVerifiedReq = (new Gson()).fromJson(body, PaymentVerifiedRequest.class);
      paymentService.handleVerifiedPayment(paymentVerifiedReq);
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
  }
}
