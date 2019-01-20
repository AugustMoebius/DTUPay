package service;

import access_bank.IBankService;
import data.IDataSource;
import domain.Transaction;
import networking.adapters.message_queue.PaymentVerifiedRequest;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.INotificationService;
import networking.notifications.exceptions.NotificationException;
import networking.ws.fastmoney.BankServiceException_Exception;

public class PaymentService {
    private IDataSource dataSource;
    private INotificationService notificationService;
    private IBankService bankService;

    public PaymentService(IDataSource dataSource, INotificationService notificationService, IBankService bankService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
        this.bankService = bankService;
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
        try {
            bankService.sendPaymentRequest(req.getCprNumber(), req.getMerchantId(), req.getPaymentAmount());
            dataSource.addTransaction(req);
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Emilie
     * @param tokenId
     */
    public void refundPayment(String tokenId) {
        Transaction transaction = dataSource.getTransaction(tokenId);
        transaction.setRefunded(true);
        dataSource.updateTransaction(transaction);
        try {
            bankService.sendPaymentRequest(transaction.getMerchantId(), transaction.getCprNumber(), transaction.getPaymentAmount());
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }
}
