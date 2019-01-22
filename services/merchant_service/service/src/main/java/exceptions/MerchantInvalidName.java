package exceptions;

import management.exceptions.MerchantServiceException;

public class MerchantInvalidName extends MerchantServiceException {
    public MerchantInvalidName(String name) {
        super("Illegal registation: Invalid name. Recieved " + name + ".");
    }
}
