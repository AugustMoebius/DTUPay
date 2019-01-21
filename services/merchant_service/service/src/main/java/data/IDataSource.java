package data;

import exceptions.MerchantNotFoundException;
import management.domain.Merchant;

public interface IDataSource {
    Merchant getMerchant(String merchantCVR) throws MerchantNotFoundException;
}
