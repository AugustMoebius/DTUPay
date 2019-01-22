package data;

import exceptions.MerchantNotFoundException;
import management.domain.CVRNumber;
import management.domain.Merchant;

public interface IDataSource {
    void registerMerchant(Merchant merchant);

    Merchant getMerchant(CVRNumber merchantCVR) throws MerchantNotFoundException;


    int getAmountOfMerchants();
}
