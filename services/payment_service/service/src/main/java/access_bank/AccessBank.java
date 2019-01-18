package access_bank;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankServiceService;

import java.math.BigDecimal;

public class AccessBank {
    BankService bank;

    public AccessBank(){
        // access module
        bank = new BankServiceService().getBankServicePort();
    }

    public void sendPaymentRequest(String customerCPR, String merchantCVR, int amount) throws BankServiceException_Exception {
        // SOAP call to the bank
        String customerAccountId = bank.getAccountByCprNumber(customerCPR).getId();
        String merchantAccountId = bank.getAccountByCprNumber(merchantCVR).getId();
        BigDecimal bd = new BigDecimal(amount);
        String transactionMessage = "Transaction using DTUPay";
        bank.transferMoneyFromTo(customerAccountId, merchantAccountId, bd, transactionMessage);
    }

}

