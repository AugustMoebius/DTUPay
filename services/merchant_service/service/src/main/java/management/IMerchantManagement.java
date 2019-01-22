package management;
import exceptions.MerchantAlreadyExistException;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import exceptions.MerchantNotFoundException;
import management.domain.CVRNumber;
import management.domain.Merchant;


public interface IMerchantManagement {

    // CRUD operations
    void registerMerchant(String firstName, String lastName, CVRNumber cvr) throws MerchantInvalidName, MerchantInvalidInformation, MerchantAlreadyExistException;

    // Read a merchant
    Merchant getMerchant(CVRNumber cvr) throws MerchantNotFoundException;

    void updateMerchant(String companyName);
    void deleteMerchant(String cvr);

}
