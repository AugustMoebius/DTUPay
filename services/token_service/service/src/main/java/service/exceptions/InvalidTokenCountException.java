package service.exceptions;

/**
 * @author August (s144461)
 */
public class InvalidTokenCountException extends TokenGenerationFailedException {
  public InvalidTokenCountException() {
    super("Must request between 1 and 5 tokens");
  }
}
