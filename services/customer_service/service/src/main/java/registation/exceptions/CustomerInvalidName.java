package registation.exceptions;

public class CustomerInvalidName extends CustomerManagerException {
    public CustomerInvalidName(String name){
        super("Illegal registation: Invalid name. Recieved " + name + ".");
    }
}
