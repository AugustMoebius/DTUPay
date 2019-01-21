package networking.adapters.message_queue.observer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Emilie
 */
public interface IObserver {
    void listen() throws IOException, TimeoutException;

}
