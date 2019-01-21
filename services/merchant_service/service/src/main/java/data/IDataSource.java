package data;

import exceptions.MerchantNotFoundException;
import management.domain.Merchant;

public interface IDataSource {
    void registerMerchant(Merchant merchant);

    Merchant getMerchant(String merchantCVR) throws MerchantNotFoundException;


}
