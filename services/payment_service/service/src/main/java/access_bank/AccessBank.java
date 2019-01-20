package access_bank;

import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;

import java.math.BigDecimal;

// TODO: Consider if this should be static
public class AccessBank implements IBankService{
    BankService bank;
    VerifyBankRequest verifyBankRequest;

    /**
     * @author Sarah
     */
    public AccessBank(){
        // access module
        bank = new BankServiceService().getBankServicePort();
        verifyBankRequest = new VerifyBankRequest();
    }

    /**
     * @author Emilie
     */
    public void sendPaymentRequest(String customerCPR, String merchantCVR, int amount) throws BankServiceException_Exception {
        //Verify CPR
        verifyBankRequest.verifyAccount(customerCPR);
        //Verify CVR
        verifyBankRequest.verifyAccount(merchantCVR);
        //Check that the amount is positive
        verifyBankRequest.verifyAmount(amount);
        // SOAP call to the bank
        String customerAccountId = bank.getAccountByCprNumber(customerCPR).getId();
        String merchantAccountId = bank.getAccountByCprNumber(merchantCVR).getId();
        BigDecimal bd = new BigDecimal(amount);
        String transactionMessage = "Transaction using DTUPay";
        bank.transferMoneyFromTo(customerAccountId, merchantAccountId, bd, transactionMessage);
    }

}

