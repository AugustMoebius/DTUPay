package exceptions;

/**
 * @author Ebbe (125015)
 */
public class InvalidCvrException extends MerchantServiceException {
    public InvalidCvrException(String cvr) {
        super("Illegal registration: Invalid CVR number. Received " + cvr + ".");
    }
}
