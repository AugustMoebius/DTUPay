package exceptions;

/**
 * @author Sebastian
 */
public class TooManyUnusedTokensException extends Exception {
    public TooManyUnusedTokensException() {
        super("Must have 1 or less tokens to request new tokens");
    }
}
