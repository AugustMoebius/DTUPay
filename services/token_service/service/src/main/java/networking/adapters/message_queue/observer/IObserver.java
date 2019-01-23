package networking.adapters.message_queue.observer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IObserver {

    /**
     * @author Esben (s172986)
     * @throws IOException
     * @throws TimeoutException
     */
    void listen() throws IOException, TimeoutException;

}
