package data;

import exceptions.MerchantNotFoundException;
import domain.CVRNumber;
import domain.Merchant;
import exceptions.InvalidCvrException;

import java.util.HashMap;

/**
 * @author Sarah (s153659), Ebbe (125015)
 */
public class InMemoryDataSource implements IDataSource {
    private static InMemoryDataSource inMemoryDataSource = new InMemoryDataSource();
    private HashMap<String, Merchant> merchants;

    public InMemoryDataSource(){
        merchants = new HashMap<String, Merchant>();
        Merchant merchant = null;
        try {
            merchant = new Merchant("Merchant", "A/S", new CVRNumber("DK11111111"));
            merchants.put(merchant.getCvr().getCvrNumber(),merchant);
        } catch (InvalidCvrException e) {
            e.printStackTrace();
        }
    }

    public static IDataSource getInstance() {
        return inMemoryDataSource;
    }

    /**
     * @author Ebbe (125015)
     * @param merchant an instance of the merchant class
     */
    @Override
    public void registerMerchant(Merchant merchant) {
        merchants.put(merchant.getCvr().getCvrNumber(), merchant);
    }

    /**
     * @author Sarah (s153659)
     * @param merchantCVR the CVR number of the merchant
     * @return
     * @throws MerchantNotFoundException
     */
    @Override
    public Merchant getMerchant(CVRNumber merchantCVR) throws MerchantNotFoundException {
        Merchant merchant = merchants.get(merchantCVR.getCvrNumber());
        if (merchant==null){
            throw new MerchantNotFoundException(merchantCVR.getCvrNumber());
        }
        return merchant;
    }

    @Override
    public int getAmountOfMerchants() {
        return merchants.size();
    }
}
