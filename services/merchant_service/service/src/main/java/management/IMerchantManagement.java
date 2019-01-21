package management;
import management.domain.Merchant;


public interface IMerchantManagement {

    // CRUD operations
    void registerMerchant(String firstName, String lastName, String cvr);
    Merchant getMerchant(String cvr);
    void updateMerchant(String companyName);
    void deleteMerchant(String cvr);

}
