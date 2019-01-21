package data;

import domain.Merchant;
import exceptions.MerchantNotFoundException;

public interface IDataSource {
    Merchant getMerchant(String merchantCVR) throws MerchantNotFoundException;
}
