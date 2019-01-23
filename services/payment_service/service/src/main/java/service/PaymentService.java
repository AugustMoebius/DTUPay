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

public class PaymentService {
    private IDataSource dataSource;
    private INotificationService notificationService;
    private IBankService bankService;

    /**
     * @author Emilie
     * @param dataSource
     * @param notificationService
     * @param bankService
     */
    public PaymentService(IDataSource dataSource, INotificationService notificationService, IBankService bankService) {
        this.dataSource = dataSource;
        this.notificationService = notificationService;
        this.bankService = bankService;
    }

    /**
     * @author August
     * @param req
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

    /**
     * @author Ebbe
     */
    private void validatePaymentRequest(PaymentRequest req) throws InvalidPaymentAmountException {
        // Negative payment
        if (req.getPaymentAmount() < 0){
            throw new InvalidPaymentAmountException("Amount cannot be negative. Amount was: " + req.getPaymentAmount());
        }
    }
}
