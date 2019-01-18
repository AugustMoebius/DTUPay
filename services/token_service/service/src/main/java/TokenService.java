import data_access_layer.IDataAccessLayer;
import domain.CPRNumber;

public class TokenService {

    private IDataAccessLayer dal;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param dal
     */
    public TokenService(IDataAccessLayer dal) {
        this.dal = dal;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @param tokenId
     * @return CPRNumber
     */
    public CPRNumber getCPRNumber(String tokenId) {
        CPRNumber cprNumber = dal.getToken(tokenId).getCPRNumber();
        return cprNumber;
    }
}
