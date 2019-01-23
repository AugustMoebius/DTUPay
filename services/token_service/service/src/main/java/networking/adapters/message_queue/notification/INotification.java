package networking.adapters.message_queue.notification;

import networking.adapters.message_queue.domain.TokenInfoVerified;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Ebbe s125015
 */
public interface INotification {

    /**
     * @author Ebbe s125015
     * @param message
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    void addMessage(TokenInfoVerified message) throws IOException, TimeoutException, InterruptedException;

}
