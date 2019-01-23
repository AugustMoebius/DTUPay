package management;
import exceptions.MerchantAlreadyExistException;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import domain.CVRNumber;
import domain.Merchant;

/**
 * @author Emilie
 */
public interface IMerchantManagement {

    // CRUD operations
    void registerMerchant(String firstName, String lastName, CVRNumber cvr) throws MerchantInvalidName, MerchantInvalidInformation, MerchantAlreadyExistException;

    // Read a merchant
    Merchant getMerchant(CVRNumber cvr) throws MerchantNotFoundException;

    /*void updateMerchant(String companyName);
    void deleteMerchant(String cvr);*/

}
