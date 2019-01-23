import access_bank.VerifyBankRequest;
import data.InMemoryDataSource;
import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;
import networking.ws.fastmoney.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VerifyRequestTest {
    private InMemoryDataSource data;
    private VerifyBankRequest verifyBankRequest;
    private BankService bank;
    String customerAccountId, merchantAccountId;
    String customerCPR, merchantCVR;

    /**
     * @author August
     */
    public VerifyRequestTest(){
        data = InMemoryDataSource.getInstance();
        verifyBankRequest = new VerifyBankRequest();
    }

    /**
     * @author Sebastian
     */
    @Before
    public void setup(){
        bank = new BankServiceService().getBankServicePort();
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
     * @author August
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

    /**
     * @author Emilie
     */
    @Test
    public void verifyRequestSucceedingTest(){
        int amount = 100;
        assertTrue(verifyBankRequest.verifyAccount(customerCPR));
        assertTrue(verifyBankRequest.verifyAccount(merchantCVR));
        assertTrue(verifyBankRequest.verifyAmount(amount));
    }

    /**
     * @author August
     */
    @Test
    public void verifyCPRFailingTest(){
        String CPR = "111113-2222";
        int amount = 100;
        assertFalse(verifyBankRequest.verifyAccount(CPR));
        assertTrue(verifyBankRequest.verifyAccount(merchantCVR));
        assertTrue(verifyBankRequest.verifyAmount(amount));

    }

    /**
     * @author Sebastian
     */
    @Test
    public void verifyCVRFailingTest(){
        String CVR = "DK52424526";
        int amount = 100;
        assertTrue(verifyBankRequest.verifyAccount(customerCPR));
        assertFalse(verifyBankRequest.verifyAccount(CVR));
        assertTrue(verifyBankRequest.verifyAmount(amount));
    }

    /**
     * @author Emilie
     */
    @Test
    public void verifyAmountFailingTest(){
        int amount = -100;
        assertTrue(verifyBankRequest.verifyAccount(customerCPR));
        assertTrue(verifyBankRequest.verifyAccount(merchantCVR));
        assertFalse(verifyBankRequest.verifyAmount(amount));
    }
}
