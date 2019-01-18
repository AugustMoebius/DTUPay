package networking.adapters.message_queue.notification;

import networking.adapters.message_queue.domain.MessageElement;

public interface INotification {

    void addMessage(MessageElement messageElement);

}
