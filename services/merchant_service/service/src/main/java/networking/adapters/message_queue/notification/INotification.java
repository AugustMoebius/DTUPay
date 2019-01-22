package networking.adapters.message_queue.notification;

import exceptions.MessagePublishException;
import networking.adapters.message_queue.domain.MerchantInfoVerified;

public interface INotification {
    void publishMessage(MerchantInfoVerified merchantInfoVerified) throws MessagePublishException;
    }
