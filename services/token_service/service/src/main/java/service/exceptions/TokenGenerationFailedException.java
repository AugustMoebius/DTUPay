package service.exceptions;

public class TokenGenerationFailedException extends Exception {
  public TokenGenerationFailedException(String message) {
    super(message);
  }
}
