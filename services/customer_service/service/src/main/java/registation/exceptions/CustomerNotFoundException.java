package registation.exceptions;

/**
 * @author Sarah
 */
public class CustomerNotFoundException extends CustomerServiceException {
    public CustomerNotFoundException(String cprNumber){
        super("Illegal registation: Not Found. Recieved " + cprNumber + ".");
    }
}