package service;

import data.IDataSource;
import networking.adapters.message_queue.PaymentVerifiedRequest;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.INotificationService;
import networking.notifications.exceptions.NotificationException;

public class PaymentService {
    private IDataSource dataSource;
    private INotificationService notificationService;

    public PaymentService(IDataSource dataSource, INotificationService notificationService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
    }

    /**
     * @author August
     * @param req
     */
    public void submitPaymentRequest(PaymentRequest req) {
        try {
            notificationService.publishPaymentInitialized(req);
        } catch (NotificationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Sebastian
     * @param req
     */
    public void handleVerifiedPayment(PaymentVerifiedRequest req) {

    }
}
