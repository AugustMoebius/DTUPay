package service.exceptions;

/**
 * @author Emilie
 */
public class InvalidPaymentAmountException extends PaymentServiceException {
  public InvalidPaymentAmountException(String message) {
    super(message);
  }
}
