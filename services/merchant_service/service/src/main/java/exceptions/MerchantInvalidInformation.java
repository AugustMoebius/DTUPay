package exceptions;

/**
 * @author Sarah
 */
public class MerchantInvalidInformation extends Throwable {
    public MerchantInvalidInformation(String s) {
        super("Invalid information.");
    }
}
