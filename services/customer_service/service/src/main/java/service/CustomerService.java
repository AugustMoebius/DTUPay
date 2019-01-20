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

public class CustomerService {
    private IDataSource dataSource;
    private ICustomerRegistration customerRegistration;

    public CustomerService(IDataSource dataSource, ICustomerRegistration customerRegistration) {
        this.dataSource = dataSource;
        this.customerRegistration = customerRegistration;
    }

    public void registerCustomer(RegisterCustomerRequest req){
        try {
            customerRegistration.addCustomer(req.getFirstName(), req.getLastName(), new CPRNumber(req.getCpr()));
        } catch (CustomerInvalidName | InvalidCprException | CustomerInvalidInformation | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomer(CPRNumber cprNumber) throws CustomerNotFoundException {
        return this.dataSource.getCustomer(cprNumber);
    }

}
