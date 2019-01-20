package registation.exceptions;

public class InvalidCprFormatException extends CustomerServiceException {

    public InvalidCprFormatException(String message) {
        super("Invalid CPR Number format. Number given: " + message);
    }
}
