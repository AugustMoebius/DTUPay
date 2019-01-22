package service.exceptions;

public class InvalidPaymentAmountException extends PaymentServiceException {
  public InvalidPaymentAmountException(String message) {
    super(message);
  }
}
