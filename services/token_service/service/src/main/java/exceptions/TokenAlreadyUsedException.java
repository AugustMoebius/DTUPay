package exceptions;

/**
 * @author August
 */
public class TokenAlreadyUsedException extends TokenServiceException {
    public TokenAlreadyUsedException(String s) {
        super(s);
    }
}
