package data_access_layer;

import domain.Token;

/**
 *
 */
public interface IDataAccessLayer {

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenID
     * @return
     */
    Token getToken(String tokenID);

}
