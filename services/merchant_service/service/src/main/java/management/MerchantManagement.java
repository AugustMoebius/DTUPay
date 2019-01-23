package management;
import data.IDataSource;
import data.InMemoryDataSource;
import exceptions.MerchantAlreadyExistException;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import domain.CVRNumber;
import domain.Merchant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MerchantManagement implements IMerchantManagement {
    private IDataSource data;
    private Merchant merchant;

    public MerchantManagement(){
        data = InMemoryDataSource.getInstance();
    }


    // CRUD - operations

    // Create a merchant

    /**
     * This method registers a merchant in the database after validating the input.
     * @author Sarah
     * @param firstName
     * @param lastName
     * @param CVR
     * @throws MerchantInvalidName
     * @throws MerchantInvalidInformation
     * @throws MerchantAlreadyExistException
     */
    @Override
    public void registerMerchant(String firstName, String lastName, CVRNumber CVR) throws MerchantInvalidName, MerchantInvalidInformation, MerchantAlreadyExistException {
        // validate first and last name
        Pattern pattern = Pattern.compile("([a-zA-Z]+)(.*)");
        Matcher matcherFirstName = pattern.matcher(firstName);
        Matcher matcherLastName = pattern.matcher(lastName);

        if(matcherFirstName.matches() && matcherLastName.matches()){
            if(!matcherFirstName.group(2).isEmpty()){
                throw new MerchantInvalidName(firstName);
            }
            if(!matcherLastName.group(2).isEmpty()){
                throw new MerchantInvalidName(lastName);
            }
        }

        if(!matcherFirstName.matches() || !matcherLastName.matches()){
            throw new MerchantInvalidInformation("");
        }

        merchant = new Merchant(firstName, lastName, CVR);
        // Add merchant to the database
        // check if the merchant already exists.
        try {
            data.getMerchant(merchant.getCvr());
            throw new MerchantAlreadyExistException(CVR);
        } catch (MerchantNotFoundException e) {
            data.registerMerchant(merchant);
        }
    }

    // Read a merchant

    /**
     * @author Sarah
     * @param cvr
     * @return
     * @throws MerchantNotFoundException
     */
    @Override
    public Merchant getMerchant(CVRNumber cvr) throws MerchantNotFoundException {
        return data.getMerchant(cvr);
    }

    /*// Update a merchant
    @Override
    public void updateMerchant(String companyName) {

    }

    // Delete a merchant
    @Override
    public void deleteMerchant(String cvr) {

    }*/

}
