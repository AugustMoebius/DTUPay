package registation.exceptions;

/**
 * @author Sarah
 */
public class CustomerInvalidName extends CustomerServiceException {
    public CustomerInvalidName(String name){
        super("Illegal registation: Invalid name. Recieved " + name + ".");
    }
}
