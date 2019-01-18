package networking.adapters.message_queue;

import service.PaymentService;

/**
 * @author : August, Sebastian
 */
public class MQObserver {
  private static MQObserver instance;

  public static MQObserver getInstance() {
    if (MQObserver.instance == null) {
      MQObserver.instance = new MQObserver();
    }

    return MQObserver.instance;
  }

  public static void init() {
    MQObserver.instance = new MQObserver();
  }

  private MQObserver() {
    // Configure MQObserver with channel, etc.

  }
}
