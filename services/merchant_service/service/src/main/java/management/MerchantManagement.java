package management;
import data.IDataSource;
import data.InMemoryDataSource;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import management.domain.CVRNumber;
import management.domain.Merchant;
import management.exceptions.InvalidCvrException;

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
    @Override
    public void registerMerchant(String firstName, String lastName, CVRNumber CVR) throws MerchantInvalidName, MerchantInvalidInformation {
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
        // Add customer to the database
        data.registerMerchant(merchant);
    }

    // Read a merchant
    @Override
    public Merchant getMerchant(CVRNumber cvr) throws MerchantNotFoundException {
        return data.getMerchant(cvr);
    }

    // Update a merchant
    @Override
    public void updateMerchant(String companyName) {

    }

    // Delete a merchant
    @Override
    public void deleteMerchant(String cvr) {

    }

}
