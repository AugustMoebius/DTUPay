package data;

import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerNotFoundException;

public interface IDataSource {

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     * @return
     * @throws CustomerNotFoundException
     */
    Customer getCustomer(CPRNumber cprNumber) throws CustomerNotFoundException;

    void addCustomer(Customer customer);
}
