package networking.adapters.message_queue;

import access_bank.AccessBank;
import com.google.gson.Gson;
import com.rabbitmq.client.*;
import data.InMemoryDataSource;
import networking.notifications.RabbitMQNotificationService;
import service.PaymentService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author : August (s144461), Sebastian (s144071)
 */
public class MQObserver {
  private static MQObserver instance;
  private final static String QUEUE_NAME = "payment_verified";
  private final static String HOST_URI = "rabbitmq-container";
  private static PaymentService paymentService = new PaymentService(
    InMemoryDataSource.getInstance(),
    new RabbitMQNotificationService(),
    new AccessBank());

  private final static String EXCHANGE_NAME = "payment_exchange";

  /**
   * @author Sebastian (s144071)
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

  /**
   * @author Sebastian (s144071)
   * @throws IOException
   * @throws TimeoutException
   */
  public static void init() throws IOException, TimeoutException {
    MQObserver.instance = new MQObserver();
  }

  /**
   * @author Sebastian (s144071)
   * @throws IOException
   * @throws TimeoutException
   */
  private MQObserver() throws IOException, TimeoutException {
    // Configure MQObserver with channel, etc.
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(HOST_URI);

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    String queueName = channel.queueDeclare().getQueue();

    // Bind queue to exchange; only subscribe to topic "payment.verified".
    channel.queueBind(queueName, EXCHANGE_NAME, "payment.verified");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String body = new String(delivery.getBody(), StandardCharsets.UTF_8);
      System.out.println("Received message on queue '" + queueName + "': " + body);


      PaymentVerifiedRequest paymentVerifiedReq = (new Gson()).fromJson(body, PaymentVerifiedRequest.class);

      paymentService.handleVerifiedPayment(paymentVerifiedReq);
    };

    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
  }
}
