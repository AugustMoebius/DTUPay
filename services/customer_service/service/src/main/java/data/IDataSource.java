package data;

import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerNotFoundException;

/**
 * @author Esben (s172986)
 */
public interface IDataSource {

    /**
     * @author Esben (s172986)
     * @param cprNumber the CPR number of the customer
     * @return
     * @throws CustomerNotFoundException
     */
    Customer getCustomer(CPRNumber cprNumber) throws CustomerNotFoundException;

    void addCustomer(Customer customer);
}
