package registation.exceptions;

public class CustomerInvalidInformation extends CustomerManagerException {
    public CustomerInvalidInformation(String s) {
        super("Illegal registation: Invalid information.");
    }
}
