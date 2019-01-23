package data;

import exceptions.MerchantNotFoundException;
import domain.CVRNumber;
import domain.Merchant;

/**
 * @author August
 */
public interface IDataSource {
    void registerMerchant(Merchant merchant);

    Merchant getMerchant(CVRNumber merchantCVR) throws MerchantNotFoundException;

    int getAmountOfMerchants();
}
