package access_bank;

import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;

import java.math.BigDecimal;

// TODO: Consider if this should be static
public class AccessBank implements IBankService{
    BankService bank;

    /**
     * @author Sarah
     */
    public AccessBank(){
        // access module
        bank = new BankServiceService().getBankServicePort();
    }

    /**
     * @author Emilie
     */
    public void sendPaymentRequest(String customerCPR, String merchantCVR, int amount) throws BankServiceException_Exception {
        // SOAP call to the bank
        String customerAccountId = bank.getAccountByCprNumber(customerCPR).getId();
        String merchantAccountId = bank.getAccountByCprNumber(merchantCVR).getId();
        BigDecimal bd = new BigDecimal(amount);
        String transactionMessage = "Transaction using DTUPay";
        bank.transferMoneyFromTo(customerAccountId, merchantAccountId, bd, transactionMessage);
    }

}

