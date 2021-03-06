package access_bank;

import networking.ws.fastmoney.BankServiceException_Exception;

/**
 * @author Emilie (s153762)
 */
public interface IBankService {
    void sendPaymentRequest(String customerCPR, String merchantCVR, int amount) throws BankServiceException_Exception;
}
