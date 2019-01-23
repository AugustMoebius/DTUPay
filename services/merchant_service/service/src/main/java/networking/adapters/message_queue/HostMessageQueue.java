package networking.adapters.message_queue;

import java.util.Properties;

/**
 * @author Sarah (s153659)
 */
public enum HostMessageQueue {
    BASE("rabbitmq-container");

    public final String url;


    HostMessageQueue(String url) {
        java.util.Properties p = new Properties();

        this.url = url;
    }
}
