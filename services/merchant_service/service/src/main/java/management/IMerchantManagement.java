package management;
import exceptions.MerchantInvalidInformation;
import exceptions.MerchantInvalidName;
import management.domain.CVRNumber;
import management.domain.Merchant;


public interface IMerchantManagement {

    // CRUD operations
    void registerMerchant(String firstName, String lastName, CVRNumber cvr) throws MerchantInvalidName, MerchantInvalidInformation;
    Merchant getMerchant(String cvr);
    void updateMerchant(String companyName);
    void deleteMerchant(String cvr);

}
