package registation.exceptions;

/**
 * @author Sarah (s153659)
 */
public class CustomerNotFoundException extends CustomerServiceException {
    public CustomerNotFoundException(String cprNumber){
        super("Illegal registation: Not Found. Recieved " + cprNumber + ".");
    }
}