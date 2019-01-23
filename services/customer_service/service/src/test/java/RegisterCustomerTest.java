import data.InMemoryDataSource;
import org.junit.Test;
import registation.CustomerRegistration;
import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerInvalidInformation;
import registation.exceptions.CustomerNotFoundException;
import registation.exceptions.CustomerInvalidName;
import registation.exceptions.InvalidCprException;

import static org.junit.Assert.assertEquals;

public class RegisterCustomerTest {
    private InMemoryDataSource data;
    private CustomerRegistration customerRegistration;

    /**
     * @author Emilie (s153762)
     */
    public RegisterCustomerTest(){
        this.data = InMemoryDataSource.getInstance();
        this.customerRegistration = new CustomerRegistration();
    }

    /**
     * @author Sarah (s153659)
     * @throws InvalidCprException
     * @throws CustomerNotFoundException
     * @throws CustomerInvalidName
     * @throws CustomerInvalidInformation
     */
    @Test
    public void registerCustomerSucceedingTest() throws InvalidCprException, CustomerNotFoundException, CustomerInvalidName, CustomerInvalidInformation {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        CPRNumber cprNumber = new CPRNumber("230387-4352");

        // register customer
        customerRegistration.addCustomer(firstName, lastName, cprNumber);

        Customer customer = customerRegistration.getCustomer();
        // check if the customer is correctly registered
        assertEquals(firstName, data.getCustomer(customer.getCprNumber()).getFirstName());
        assertEquals(lastName, data.getCustomer(customer.getCprNumber()).getLastName());
        assertEquals(cprNumber, data.getCustomer(customer.getCprNumber()).getCprNumber());
    }

    /**
     * @author Sarah (s153659)
     * @throws InvalidCprException
     * @throws CustomerInvalidInformation
     * @throws CustomerInvalidName
     */
    @Test(expected = CustomerInvalidName.class)
    public void registerCustomerWithInvalidNameTest() throws InvalidCprException, CustomerInvalidInformation, CustomerInvalidName {
        // Data
        String firstName = "Sera8";
        String lastName = "Ha8nsen";
        CPRNumber cprNumber = new CPRNumber("230387-4352");

        int customersInDatabase = data.getAmountOfCustomers();

        // try to register customer
        try {
            customerRegistration.addCustomer(firstName, lastName, cprNumber);
        } catch (CustomerInvalidName e) {
            assertEquals("Illegal registation: Invalid name. Recieved " + firstName + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase, data.getAmountOfCustomers());

            throw e;
        }
    }

    /**
     * @author Emilie (s153762)
     * @throws InvalidCprException
     * @throws CustomerInvalidName
     * @throws CustomerInvalidInformation
     */
    @Test(expected = InvalidCprException.class)
    public void registerCustomerWithInvalidCPRTest() throws InvalidCprException, CustomerInvalidName, CustomerInvalidInformation {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        String cprString = "999999-999";

        int customersInDatabase = data.getAmountOfCustomers();

        // try/catch to verify error message
        try {
            CPRNumber cprNumber = new CPRNumber(cprString);
            customerRegistration.addCustomer(firstName, lastName, cprNumber);
        } catch (InvalidCprException e) {
            assertEquals("Illegal registation: Invalid CPR number. Recieved " + cprString + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase, data.getAmountOfCustomers());

            throw e;
        }
    }
}
