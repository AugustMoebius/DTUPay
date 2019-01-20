package registation.exceptions;

public class CustomerInvalidInformation extends CustomerServiceException {
    public CustomerInvalidInformation(String s) {
        super("Illegal registation: Invalid information.");
    }
}
