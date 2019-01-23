package exceptions;

/**
 * @author Sarah (s153659)
 */
public class MerchantInvalidName extends MerchantServiceException {
    public MerchantInvalidName(String name) {
        super("Illegal registration: Invalid name. Received " + name + ".");
    }
}
