import access_bank.AccessBank;
import data.InMemoryDataSource;
import networking.adapters.message_queue.PaymentVerifiedRequest;
import networking.adapters.rest.RestApplication;
import networking.adapters.rest.requests.PaymentRequest;
import networking.notifications.RabbitMQNotificationService;
import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;
import networking.ws.fastmoney.User;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.PaymentService;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RefundPaymentTest {
    private final PaymentService paymentService;
    private BankService bank;
    private String customerCPR, merchantCVR;
    private AccessBank accessBank;
    private String customerAccountId, merchantAccountId;

    public RefundPaymentTest(){
        paymentService = new PaymentService(InMemoryDataSource.getInstance(), new RabbitMQNotificationService(), new AccessBank());
    }

    @Before
    public void setup(){
        bank = new BankServiceService().getBankServicePort();
        accessBank = new AccessBank();
        // Create customer, merchant and an amount.
        customerCPR = "111111-2222";
        merchantCVR = "DK52424524";

        // Create users
        User customer = new User();
        customer.setCprNumber(customerCPR);
        customer.setFirstName("Peter");
        customer.setLastName("Jensen");
        User merchant = new User();
        merchant.setCprNumber(merchantCVR);
        merchant.setFirstName("Hans");
        merchant.setLastName("A/S");

        // Create balances
        BigDecimal balance = new BigDecimal(200);

        // Create accounts
        try {
            customerAccountId = bank.createAccountWithBalance(customer, balance);
            merchantAccountId = bank.createAccountWithBalance(merchant, balance);
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Emilie
     */
    @After
    public void tearDown(){
        try {
            bank.retireAccount(customerAccountId);
            bank.retireAccount(merchantAccountId);
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void RefundMoneyTest(){
        int paymentAmount = 100;
        String tokenId = "test";
        try {
            accessBank.sendPaymentRequest(customerCPR, merchantCVR, paymentAmount);
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }

        PaymentVerifiedRequest paymentVerifiedRequest = new PaymentVerifiedRequest(this.merchantCVR, paymentAmount, tokenId, customerCPR);
        paymentService.handleVerifiedPayment(paymentVerifiedRequest);
        paymentService.refundPayment(tokenId);

        assertTrue(InMemoryDataSource.getInstance().getTransaction(tokenId).isRefunded());
    }
}
