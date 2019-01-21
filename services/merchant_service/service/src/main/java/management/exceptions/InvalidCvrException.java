package management.exceptions;

public class InvalidCvrException extends MerchantServiceException {
    public InvalidCvrException(String cvr) {
        super("Illegal registration: Invalid CVR number. Received " + cvr + ".");
    }
}
