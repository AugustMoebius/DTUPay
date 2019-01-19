package registation.exceptions;

public class CustomerNotFoundException extends CustomerManagerException {
    public CustomerNotFoundException(String cprNumber){
        super("Illegal registation: Not Found. Recieved " + cprNumber + ".");
    }
}