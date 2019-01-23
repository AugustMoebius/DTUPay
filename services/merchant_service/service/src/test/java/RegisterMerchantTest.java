import data.IDataSource;
import data.InMemoryDataSource;
import exceptions.MerchantAlreadyExistException;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import management.MerchantManagement;
import domain.CVRNumber;
import domain.Merchant;
import exceptions.InvalidCvrException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterMerchantTest {
    private IDataSource data;
    private MerchantManagement merchantManagement;

    /**
     * @author Sarah
     */
    public RegisterMerchantTest(){
        this.data = InMemoryDataSource.getInstance();
        this.merchantManagement = new MerchantManagement();
    }

    /**
     * @author Sarah
     * @throws InvalidCvrException
     * @throws MerchantInvalidInformation
     * @throws MerchantInvalidName
     * @throws MerchantNotFoundException
     * @throws MerchantAlreadyExistException
     */
    @Test
    public void registerMerchantSucceedingTest() throws InvalidCvrException, MerchantInvalidInformation, MerchantInvalidName, MerchantNotFoundException, MerchantAlreadyExistException {
        // Data
        String firstName = "Clara";
        String lastName = "Hansen";
        CVRNumber cvrNumber = new CVRNumber("DK86753467");

        // register customer
        merchantManagement.registerMerchant(firstName, lastName, cvrNumber);

        Merchant merchant = merchantManagement.getMerchant(cvrNumber);
        // check if the customer is correctly registered
        assertEquals(firstName, data.getMerchant(merchant.getCvr()).getFirstName());
        assertEquals(lastName, data.getMerchant(merchant.getCvr()).getLastName());
        assertEquals(cvrNumber, data.getMerchant(merchant.getCvr()).getCvr());
    }

    /**
     * @author Sarah
     * @throws InvalidCvrException
     * @throws MerchantInvalidInformation
     * @throws MerchantInvalidName
     * @throws MerchantAlreadyExistException
     */
    @Test(expected = MerchantInvalidName.class)
    public void registerMerchantWithInvalidNameTest() throws InvalidCvrException, MerchantInvalidInformation, MerchantInvalidName, MerchantAlreadyExistException {
        // Data
        String firstName = "Sera8";
        String lastName = "Ha8nsen";
        CVRNumber cvrNumber = new CVRNumber("DK98567856");

        int customersInDatabase = data.getAmountOfMerchants();

        // try to register customer
        try {
            merchantManagement.registerMerchant(firstName, lastName, cvrNumber);
        } catch (MerchantInvalidName e) {
            assertEquals("Illegal registration: Invalid name. Received " + firstName + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase, data.getAmountOfMerchants());

            throw e;
        }
    }

    /**
     * @author Sarah
     * @throws InvalidCvrException
     * @throws MerchantInvalidName
     * @throws MerchantInvalidInformation
     * @throws MerchantAlreadyExistException
     */
    @Test(expected = InvalidCvrException.class)
    public void registerMerchantWithInvalidCPRTest() throws InvalidCvrException, MerchantInvalidName, MerchantInvalidInformation, MerchantAlreadyExistException {
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

    /**
     * @author Sarah
     * @throws MerchantAlreadyExistException
     * @throws MerchantInvalidInformation
     * @throws MerchantInvalidName
     * @throws InvalidCvrException
     */
    @Test(expected = MerchantAlreadyExistException.class)
    public void registerMerchantAlreadyExistsTest() throws MerchantAlreadyExistException, MerchantInvalidInformation, MerchantInvalidName, InvalidCvrException {
        String firstName = "Clara";
        String lastName = "Hansen";
        String cvrString = "DK73648292";

        int customersInDatabase = data.getAmountOfMerchants();

        CVRNumber cvrNumber = new CVRNumber(cvrString);

        // try/catch to verify error message
        try {
            merchantManagement.registerMerchant(firstName, lastName, cvrNumber);
            merchantManagement.registerMerchant(firstName, lastName, cvrNumber);
        } catch (MerchantAlreadyExistException e) {
            assertEquals("Illegal registration: Merchant already exists. Received " + cvrString + ".", e.getMessage());

            // Check that the customer is not added to the database
            assertEquals(customersInDatabase+1, data.getAmountOfMerchants());

            throw e;
        }
    }

}
