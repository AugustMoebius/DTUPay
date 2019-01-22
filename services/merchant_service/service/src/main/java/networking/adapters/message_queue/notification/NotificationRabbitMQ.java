package networking.adapters.message_queue.notification;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import exceptions.MessagePublishException;
import networking.adapters.message_queue.HostMessageQueue;
import networking.adapters.message_queue.domain.MerchantInfoVerified;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @autor Sarah
 */
public class NotificationRabbitMQ implements INotification {
    public NotificationRabbitMQ(){}
    private final static String EXCHANGE_NAME = "payment_exchange";

    /**
     * @author Emilie
     * @param merchantInfoVerified
     */
    @Override
    public void publishMessage(MerchantInfoVerified merchantInfoVerified) throws MessagePublishException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HostMessageQueue.BASE.url);

        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String routingKey = "payment.verified";

            Gson gson = new Gson();
            String merchantInfoVerifiedJson = gson.toJson(merchantInfoVerified);

            channel.basicPublish(EXCHANGE_NAME,routingKey,null,merchantInfoVerifiedJson.getBytes());
            System.out.println(" [verified] Sent '" + merchantInfoVerifiedJson + "'");

        } catch (TimeoutException | IOException e) {
            throw new MessagePublishException(e.getLocalizedMessage());
        }

    }


}
