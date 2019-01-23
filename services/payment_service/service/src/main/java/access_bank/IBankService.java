package access_bank;

import networking.ws.fastmoney.BankServiceException_Exception;

/**
 * @author Emilie
 */
public interface IBankService {
    void sendPaymentRequest(String customerCPR, String merchantCVR, int amount) throws BankServiceException_Exception;
}
