package registation.exceptions;

/**
 * @author Sarah (s153659)
 */
public class CustomerInvalidInformation extends CustomerServiceException {
    public CustomerInvalidInformation(String s) {
        super("Illegal registation: Invalid information.");
    }
}
