package service;

import access_bank.IBankService;
import data.IDataSource;
import domain.Transaction;
import networking.adapters.message_queue.PaymentVerifiedRequest;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.INotificationService;
import networking.notifications.exceptions.NotificationException;
import networking.ws.fastmoney.BankServiceException_Exception;
import service.exceptions.InvalidPaymentAmountException;

/**
 * @author Emilie (s153762), August (s144461), Sebastian (s144071), Ebbe (s125015)
 */
public class PaymentService {
    private IDataSource dataSource;
    private INotificationService notificationService;
    private IBankService bankService;

    /**
     * @author Emilie (s153762)
     * @param dataSource the interface for the data source
     * @param notificationService the interface for the notification service
     * @param bankService the interface for the bank service
     */
    public PaymentService(IDataSource dataSource, INotificationService notificationService, IBankService bankService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
        this.bankService = bankService;
    }

    /**
     * @author August (s144461)
     * @param req the PaymentRequest instance
     */
    public void submitPaymentRequest(PaymentRequest req) throws InvalidPaymentAmountException {
        System.out.println("Paymentservice.submitPaymentRequest");
        validatePaymentRequest(req);

        try {
            notificationService.publishPaymentInitialized(req);
            System.out.println("Pushed notification for: " + req.getMerchantId());
        } catch (NotificationException e) {
            e.printStackTrace();
        }
    }


    /**
     * @author Sebastian (s144071)
     * @param req the PaymentVerifiedRequest instance
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
     * @author Emilie (s153762)
     * @param tokenId the identification of a token
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

    /**
     * @author Ebbe (s125015)
     * @param req the PaymentRequest instance
     * @throws InvalidPaymentAmountException
     */
    private void validatePaymentRequest(PaymentRequest req) throws InvalidPaymentAmountException {
        // Negative payment
        if (req.getPaymentAmount() < 0){
            throw new InvalidPaymentAmountException("Amount cannot be negative. Amount was: " + req.getPaymentAmount());
        }
    }
}
