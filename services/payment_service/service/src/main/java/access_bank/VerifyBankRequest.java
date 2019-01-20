package access_bank;

import networking.ws.fastmoney.Account;
import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;

public class VerifyBankRequest {
    BankService bank;

    public VerifyBankRequest(){
        // access module
        bank = new BankServiceService().getBankServicePort();
    }


    public boolean verifyAccount(String id) {
        try {
            bank.getAccountByCprNumber(id);
        } catch (BankServiceException_Exception e){
            return false;
        }
        return true;
    }

    public boolean verifyAmount(int amount) {
        return amount > 0;
    }

}
