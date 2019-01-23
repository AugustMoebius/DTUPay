package registation.exceptions;

/**
 * @author Sarah
 */
public class CustomerInvalidInformation extends CustomerServiceException {
    public CustomerInvalidInformation(String s) {
        super("Illegal registation: Invalid information.");
    }
}
