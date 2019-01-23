package networking.adapters.message_queue.observer;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import data.IDataSource;
import data.MockDatabase;
import data.exceptions.TokenNotFoundException;
import domain.CPRNumber;
import exceptions.TokenAlreadyUsedException;
import networking.adapters.message_queue.HostMessageQueue;
import networking.adapters.message_queue.domain.TokenInfo;
import networking.adapters.message_queue.domain.TokenInfoVerified;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import service.TokenService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public final class ObserverRabbitMQ implements IObserver {

    private static final ObserverRabbitMQ observerRabbitMQ = new ObserverRabbitMQ();

    private final static String QUEUE_NAME = "payment_initialized";
    private final static String EXCHANGE_NAME = "payment_exchange";
    //private final static String QUEUE_NAME = "token";

    private IDataSource data;
    private TokenService tokenService;
    private NotificationRabbitMQ notificationRabbitMQ;

    /**
     * @author August (s144461)
     */
    private ObserverRabbitMQ() {
        this.data = MockDatabase.getInstance();
        this.notificationRabbitMQ = new NotificationRabbitMQ();
        this.tokenService = new TokenService(this.data, this.notificationRabbitMQ);
    }

    public static ObserverRabbitMQ getInstance() {
        return observerRabbitMQ;
    }

    /**
     * @author Esben (s172986)
     * @throws IOException
     * @throws TimeoutException
     */
    public void listen() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HostMessageQueue.BASE.url);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "payment.initialized");

        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String responseMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [initilized] Received '" + responseMessage + "'");

            Gson gson = new Gson();
            TokenInfo tokenInfo = gson.fromJson(responseMessage, TokenInfo.class);

            try {
                this.tokenService.handleTokenInfo(tokenInfo);
            } catch (TokenAlreadyUsedException | TokenNotFoundException e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
