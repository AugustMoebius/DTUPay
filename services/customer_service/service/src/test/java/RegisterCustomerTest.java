import com.sun.media.sound.InvalidFormatException;
import data.InMemoryDataSource;
import org.junit.Test;
import registation.CustomerRegistration;
import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerInvalidInformation;
import registation.exceptions.CustomerNotFoundException;
import registation.exceptions.CustomerInvalidName;

import static org.junit.Assert.assertEquals;

public class RegisterCustomerTest {
    private InMemoryDataSource data;
    private CustomerRegistration customerRegistration;


    public RegisterCustomerTest(){
        this.data = InMemoryDataSource.getInstance();
        this.customerRegistration = new CustomerRegistration();
    }

    @Test
    public void registerCustomerSucceedingTest() throws InvalidFormatException, CustomerNotFoundException, CustomerInvalidName, CustomerInvalidInformation {
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

    @Test
    public void registerCustomerWithInvalidNameTest() throws InvalidFormatException {
        // Data
        String firstName = "Sera8";
        String lastName = "Ha8nsen";
        CPRNumber cprNumber = new CPRNumber("230387-4352");

        int customersInDatabase = data.getAmountOfCustomers();

        // try to register customer
        try {
            customerRegistration.addCustomer(firstName, lastName, cprNumber);
        } catch (CustomerInvalidName e){
            assertEquals("Illegal registation: Invalid name. Recieved " + firstName + ".", e.getMessage());
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (CustomerInvalidInformation customerInvalidInformation) {
            customerInvalidInformation.printStackTrace();
        }
        // Check that the customer is not added to the database
        assertEquals(customersInDatabase, data.getAmountOfCustomers());
    }

    @Test
    public void registerCustomerWithInvalidCPRTest() throws InvalidFormatException {
        // Data
        String firstName = "Sera8";
        String lastName = "Ha8nsen";
        CPRNumber cprNumber = new CPRNumber("230387-1111");

        int customersInDatabase = data.getAmountOfCustomers();

        // Try to register customer
        try {
            customerRegistration.addCustomer(firstName, lastName, cprNumber);
        } catch (InvalidFormatException e){
            assertEquals("Illegal registation: Invalid CPR number. Recieved " + cprNumber + ".", e.getMessage());
        } catch (CustomerInvalidInformation customerInvalidInformation) {
            customerInvalidInformation.printStackTrace();
        } catch (CustomerInvalidName customerInvalidName) {
            customerInvalidName.printStackTrace();
        }

        // Check that the customer is not added to the database
        assertEquals(customersInDatabase, data.getAmountOfCustomers());
    }

}
