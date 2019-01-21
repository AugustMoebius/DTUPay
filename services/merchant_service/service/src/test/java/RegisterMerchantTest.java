import data.IDataSource;
import data.InMemoryDataSource;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import management.MerchantManagement;
import management.domain.CVRNumber;
import management.domain.Merchant;
import management.exceptions.InvalidCvrException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterMerchantTest {
    private IDataSource data;
    private MerchantManagement merchantManagement;


    public RegisterCustomerTest(){
        this.data = InMemoryDataSource.getInstance();
        this.merchantManagement = new MerchantManagement();
    }

    @Test
    public void registerCustomerSucceedingTest() throws InvalidCvrException, MerchantInvalidInformation, MerchantInvalidName, MerchantNotFoundException {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        CVRNumber cvrNumber = new CVRNumber("DK86753467");

        // register customer
        merchantManagement.registerMerchant(firstName, lastName, cvrNumber);

        Merchant merchant = merchantManagement.getMerchant(cvrNumber.toString());
        // check if the customer is correctly registered
        assertEquals(firstName, data.getMerchant(merchant.getCvr().toString()).getFirstName());
        assertEquals(lastName, data.getMerchant(merchant.getCvr().toString()).getLastName());
        assertEquals(cvrNumber, data.getMerchant(merchant.getCvr().toString()).getCprNumber());
    }

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

    @Test(expected = InvalidCprException.class)
    public void registerCustomerWithInvalidCPRTest() throws InvalidCprException, CustomerInvalidName, CustomerInvalidInformation {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        String cprString = "999999-9999";

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
