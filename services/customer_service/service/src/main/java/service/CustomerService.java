package service;

import com.sun.media.sound.InvalidFormatException;
import data.IDataSource;
import networking.adapters.rest.requests.RegisterCustomerRequest;
import registation.ICustomerRegistration;
import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerInvalidInformation;
import registation.exceptions.CustomerInvalidName;
import registation.exceptions.InvalidCprException;
import registation.exceptions.CustomerNotFoundException;

/**
 * @author Emilie (s153762), Sarah (s153659), Esben (s172986)
 */
public class CustomerService {
    private IDataSource dataSource;
    private ICustomerRegistration customerRegistration;

    /**
     * @author Emilie (s153762)
     * @param dataSource the interface of the datasource
     * @param customerRegistration the interface of the customerregistration
     */
    public CustomerService(IDataSource dataSource, ICustomerRegistration customerRegistration) {
        this.dataSource = dataSource;
        this.customerRegistration = customerRegistration;
    }

    /**
     * @author Sarah (s153659)
     * @param req an instance of the RegisterCustomerRequest class
     */
    public void registerCustomer(RegisterCustomerRequest req){
        try {
            customerRegistration.addCustomer(req.getFirstName(), req.getLastName(), new CPRNumber(req.getCpr()));
        } catch (CustomerInvalidName | InvalidCprException | CustomerInvalidInformation | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Esben (s172986)
     * @param cprNumber the CPR number of a customer
     * @return customer
     * @throws CustomerNotFoundException
     */
    public Customer getCustomer(CPRNumber cprNumber) throws CustomerNotFoundException {
        return this.dataSource.getCustomer(cprNumber);
    }

}
