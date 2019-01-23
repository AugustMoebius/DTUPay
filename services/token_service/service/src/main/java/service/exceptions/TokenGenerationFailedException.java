package service.exceptions;

/**
 * @author Sebastian
 */
public class TokenGenerationFailedException extends Exception {
  public TokenGenerationFailedException(String message) {
    super(message);
  }
}
