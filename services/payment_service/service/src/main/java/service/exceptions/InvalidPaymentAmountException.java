package service.exceptions;

/**
 * @author Emilie (s153762)
 */
public class InvalidPaymentAmountException extends PaymentServiceException {
  public InvalidPaymentAmountException(String message) {
    super(message);
  }
}
