import data.IDataSource;
import data.InMemoryDataSource;
import exceptions.MerchantNotFoundException;
import management.domain.Merchant;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class getMerchantTest {

    @Test
    public void getMerchantTest(){
        IDataSource data = InMemoryDataSource.getInstance();
        try {
            Merchant merchant = data.getMerchant("DK11111111");
            assertEquals("DK11111111",merchant.getId());

        } catch (MerchantNotFoundException e) {
            fail();
        }
    }
}
