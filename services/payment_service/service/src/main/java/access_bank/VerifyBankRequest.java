package access_bank;

import networking.ws.fastmoney.BankService;
import networking.ws.fastmoney.BankServiceException_Exception;
import networking.ws.fastmoney.BankServiceService;

/**
 * @author August (s144461)
 */
public class VerifyBankRequest {
    BankService bank;


    public VerifyBankRequest(){
        // access module
        bank = new BankServiceService().getBankServicePort();
    }

    /**
     * @author August (s144461)
     * @param id the identification of the account
     * @return TRUE or FALSE
     */
    public boolean verifyAccount(String id) {
        try {
            bank.getAccountByCprNumber(id);
        } catch (BankServiceException_Exception e){
            return false;
        }
        return true;
    }

    /**
     * @author August (s144461)
     * @param amount the amount to be verified
     * @return amount as an integer
     */
    public boolean verifyAmount(int amount) {
        return amount > 0;
    }

}
