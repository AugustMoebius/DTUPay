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


    public RegisterMerchantTest(){
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

        Merchant merchant = merchantManagement.getMerchant(cvrNumber);
        // check if the customer is correctly registered
        assertEquals(firstName, data.getMerchant(merchant.getCvr().toString()).getFirstName());
        assertEquals(lastName, data.getMerchant(merchant.getCvr().toString()).getLastName());
        assertEquals(cvrNumber, data.getMerchant(merchant.getCvr().toString()).getCvr());
    }

    @Test(expected = MerchantInvalidName.class)
    public void registerCustomerWithInvalidNameTest() throws InvalidCvrException, MerchantInvalidInformation, MerchantInvalidName {
        // Data
        String firstName = "Sera8";
        String lastName = "Ha8nsen";
        CVRNumber cvrNumber = new CVRNumber("DK98567856");

        int customersInDatabase = data.getAmountOfMerchants();

        // try to register customer
        try {
            merchantManagement.registerMerchant(firstName, lastName, cvrNumber);
        } catch (MerchantInvalidName e) {
            assertEquals("Illegal registation: Invalid name. Recieved " + firstName + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase, data.getAmountOfMerchants());

            throw e;
        }
    }

    @Test(expected = InvalidCvrException.class)
    public void registerCustomerWithInvalidCPRTest() throws InvalidCvrException, MerchantInvalidName, MerchantInvalidInformation {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        String cvrString = "DK7364842924";

        int customersInDatabase = data.getAmountOfMerchants();

        // try/catch to verify error message
        try {
            CVRNumber cvrNumber = new CVRNumber(cvrString);
            merchantManagement.registerMerchant(firstName, lastName, cvrNumber);
        } catch (InvalidCvrException e) {
            assertEquals("Illegal registration: Invalid CVR number. Received " + cvrString + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase, data.getAmountOfMerchants());

            throw e;
        }
    }

}
