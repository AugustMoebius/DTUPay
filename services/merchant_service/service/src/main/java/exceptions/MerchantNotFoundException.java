package exceptions;

/**
 * @author Sarah
 */
public class MerchantNotFoundException extends MerchantServiceException {
    public MerchantNotFoundException(String message){
        super(message);
    }
}
