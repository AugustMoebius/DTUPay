package exceptions;

/**
 * @author Sarah (s153659)
 */
public class MerchantNotFoundException extends MerchantServiceException {
    public MerchantNotFoundException(String message){
        super(message);
    }
}
