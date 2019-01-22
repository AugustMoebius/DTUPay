package data;

import exceptions.MerchantNotFoundException;
import management.domain.Merchant;

import java.util.HashMap;

/**
 * @author Sarah
 */
public class InMemoryDataSource implements IDataSource {
    private static InMemoryDataSource inMemoryDataSource = new InMemoryDataSource();
    private HashMap<String, Merchant> merchants;

    public InMemoryDataSource(){
        merchants = new HashMap<String, Merchant>();
        Merchant merchant = new Merchant("Merchant", "DK11111111");
        merchants.put(merchant.getId(),merchant);
    }

    public static IDataSource getInstance() {
        return inMemoryDataSource;
    }

    @Override
    public Merchant getMerchant(String merchantCVR) throws MerchantNotFoundException {
        Merchant merchant = merchants.get(merchantCVR);
        if (merchant==null){
            throw new MerchantNotFoundException();
        }
        return merchant;
    }
}
