package networking.adapters.message_queue.notification;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import networking.adapters.message_queue.domain.MessageElement;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NotificationRabbitMQ implements INotification {

    private final static String QUEUE_NAME = "receive";

    public void sendMessage() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("02267-munich.compute.dtu.dk");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            while(true){
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    public void addMessage(MessageElement messageElement) {

    }
}
