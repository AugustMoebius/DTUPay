package exceptions;

import management.domain.CVRNumber;

public class MerchantAlreadyExistException extends Throwable {
    public MerchantAlreadyExistException(CVRNumber cvr) {
        super("Illegal registration: Merchant already exists. Received " + cvr.getCvrNumber() + ".");
    }
}
