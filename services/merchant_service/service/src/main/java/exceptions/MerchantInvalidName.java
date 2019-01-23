package exceptions;

/**
 * @author Sarah
 */
public class MerchantInvalidName extends MerchantServiceException {
    public MerchantInvalidName(String name) {
        super("Illegal registration: Invalid name. Received " + name + ".");
    }
}
