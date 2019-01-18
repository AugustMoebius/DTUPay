package networking.adapters.message_queue.observer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import data.IDataSource;
import data.MockDatabase;
import domain.CPRNumber;
import networking.adapters.message_queue.HostMessageQueue;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import service.TokenService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public final class ObserverRabbitMQ implements IObserver {

    private static final ObserverRabbitMQ observerRabbitMQ = new ObserverRabbitMQ();

    private final static String QUEUE_NAME = "payment_initialized";
    //private final static String QUEUE_NAME = "token";

    private IDataSource data;
    private TokenService tokenService;
    private NotificationRabbitMQ notificationRabbitMQ;

    private ObserverRabbitMQ() {
        this.data = MockDatabase.getInstance();
        this.notificationRabbitMQ = new NotificationRabbitMQ();
        this.tokenService = new TokenService(this.data, this.notificationRabbitMQ);
    }

    public static ObserverRabbitMQ getInstance() {
        return observerRabbitMQ;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @throws IOException
     * @throws TimeoutException
     */
    public void listen() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HostMessageQueue.BASE.url);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String responseMessage = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + responseMessage + "'");

            Gson gson = new Gson();
            TokenInfo tokenInfo = gson.fromJson(responseMessage, TokenInfo.class);

            CPRNumber cprNumber = this.tokenService.getCPRNumber(tokenInfo.getTokenId());

            TokenInfoVerified tokenInfoVerified =
                    new TokenInfoVerified(tokenInfo.getMerchantId(),
                            tokenInfo.getPaymentAmount(),
                            tokenInfo.getTokenId(),
                            cprNumber);

            try {
                notificationRabbitMQ.addMessage(tokenInfoVerified);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
