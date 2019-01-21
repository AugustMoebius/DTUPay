package management.exceptions;

public class InvalidCvrException extends MerchantServiceException {
    public InvalidCvrException(String cvr) {
        super("Invalid Registration: Invalid CVR. Received " + cvr + ".");
    }
}
