package service.exceptions;

/**
 * @author Sebastian (s144071)
 */
public class TokenGenerationFailedException extends Exception {
  public TokenGenerationFailedException(String message) {
    super(message);
  }
}
