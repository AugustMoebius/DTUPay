package data;

import exceptions.MerchantNotFoundException;
import management.domain.CVRNumber;
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
    }

    public static IDataSource getInstance() {
        return inMemoryDataSource;
    }

    @Override
    public void registerMerchant(Merchant merchant) {
        merchants.put(merchant.getCvr().getCvrNumber(), merchant);
    }

    @Override
    public Merchant getMerchant(CVRNumber merchantCVR) throws MerchantNotFoundException {
        Merchant merchant = merchants.get(merchantCVR.getCvrNumber());
        if (merchant==null){
            throw new MerchantNotFoundException();
        }
        return merchant;
    }

    @Override
    public int getAmountOfMerchants() {
        return merchants.size();
    }
}
