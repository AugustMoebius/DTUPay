package networking.adapters.message_queue.observer;

import com.google.gson.Gson;
import com.rabbitmq.client.*;
import data.IDataSource;
import data.InMemoryDataSource;
import management.MerchantManagement;
import networking.adapters.message_queue.HostMessageQueue;
import networking.adapters.message_queue.domain.PaymentInitializedRequest;
import networking.adapters.message_queue.notification.INotification;
import networking.adapters.message_queue.notification.NotificationRabbitMQ;
import service.MerchantService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ObserverRabbitMQ implements IObserver{
    private final static ObserverRabbitMQ observerRabbitMQ = new ObserverRabbitMQ();
    private final static String EXCHANGE_NAME = "payment_exchange";
    private final MerchantService merchantService;
    private final MerchantManagement merchantManagement;
    private final IDataSource data;
    private final INotification notificationRabbitMQ;

    public static ObserverRabbitMQ getInstance() {
        return observerRabbitMQ;
    }

    /**
     * @author Emilie (s153762)
     */
    private ObserverRabbitMQ() {
        this.merchantManagement = new MerchantManagement();
        this.data = InMemoryDataSource.getInstance();
        this.notificationRabbitMQ = new NotificationRabbitMQ();
        this.merchantService = new MerchantService(this.data, this.merchantManagement, this.notificationRabbitMQ);
    }


    /**
     * @author Sarah (s153659)
     * @throws IOException
     * @throws TimeoutException
     */
    @Override
    public void listen() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HostMessageQueue.BASE.url);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName,EXCHANGE_NAME,"payment.signed");
        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String responseMessage = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [signed] Received '" + responseMessage + "'");

            Gson gson = new Gson();
            PaymentInitializedRequest paymentInitializedRequest = gson.fromJson(responseMessage, PaymentInitializedRequest.class);
            this.merchantService.handlePaymentInitialized(paymentInitializedRequest);
        };

        channel.basicConsume(queueName, true,deliverCallback,consumerTag -> {});
    }

}
