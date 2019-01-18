package networking.adapters.message_queue.observer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IObserver {
    void listen() throws IOException, TimeoutException;
}
