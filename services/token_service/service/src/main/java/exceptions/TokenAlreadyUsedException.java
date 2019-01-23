package exceptions;

/**
 * @author August (s144461)
 */
public class TokenAlreadyUsedException extends TokenServiceException {
    public TokenAlreadyUsedException(String s) {
        super(s);
    }
}
