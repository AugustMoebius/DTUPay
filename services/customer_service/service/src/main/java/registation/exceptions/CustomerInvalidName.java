package registation.exceptions;

/**
 * @author Sarah (s153659)
 */
public class CustomerInvalidName extends CustomerServiceException {
    public CustomerInvalidName(String name){
        super("Illegal registation: Invalid name. Recieved " + name + ".");
    }
}
