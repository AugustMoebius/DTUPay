import access_bank.AccessBank;
import networking.ws.fastmoney.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccessBankTest {
    BankService bank;
    String customerAccountId, merchantAccountId;
    String customerCPR, merchantCVR;
    AccessBank accessBank;

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
    public void transferMoneyTest(){
        int amount = 100;

        try {
            // send payment request
            accessBank.sendPaymentRequest(customerCPR, merchantCVR, amount);
            // Check balances
            assertEquals(new BigDecimal(100), bank.getAccountByCprNumber(customerCPR).getBalance());
            assertEquals(new BigDecimal(300), bank.getAccountByCprNumber(merchantCVR).getBalance());
        } catch (BankServiceException_Exception e) {
            e.printStackTrace();
        }
    }

}
