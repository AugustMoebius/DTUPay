package networking.notifications;

import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.exceptions.NotificationException;

/**
 * @author August (s144461)
 */
public interface INotificationService {
    void publishPaymentInitialized(PaymentRequest req) throws NotificationException;
}
