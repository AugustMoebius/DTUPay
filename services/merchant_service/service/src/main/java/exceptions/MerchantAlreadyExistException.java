package exceptions;

import domain.CVRNumber;

/**
 * @author Sarah (s153659)
 */
public class MerchantAlreadyExistException extends MerchantServiceException {
    public MerchantAlreadyExistException(CVRNumber cvr) {
        super("Illegal registration: Merchant already exists. Received " + cvr.getCvrNumber() + ".");
    }
}
