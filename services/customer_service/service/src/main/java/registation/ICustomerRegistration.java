package registation;

import com.sun.media.sound.InvalidFormatException;
import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerInvalidInformation;
import registation.exceptions.CustomerInvalidName;

/**
 * @author Sarah (s153659)
 */
public interface ICustomerRegistration {
    void addCustomer(String firstName, String lastName, CPRNumber cprNumber) throws CustomerInvalidName, CustomerInvalidInformation, InvalidFormatException;
    Customer getCustomer();
}
