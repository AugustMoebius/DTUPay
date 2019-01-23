package data;

import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerNotFoundException;
import registation.exceptions.InvalidCprException;

import java.util.HashMap;

/**
 * @author Sarah (s153659), Emilie (s153762)
 */
public class InMemoryDataSource implements IDataSource {
    private static final InMemoryDataSource dataSource = new InMemoryDataSource();
    private HashMap<String, Customer> allCustomers;

    public static InMemoryDataSource getInstance(){
        return dataSource;
    }

    /**
     * @author Sarah (s153659)
     */
    public InMemoryDataSource() {
        allCustomers = new HashMap<String, Customer>();

        //registation.domain.registation generation
        Customer customer1, customer2, customer3, customer4 = null;
        try {
            customer1 = new Customer("Peter", "Hansen", new CPRNumber("120594-4383"));
            customer2 = new Customer("Hans", "Jensen", new CPRNumber("160792-3373"));
            customer3 =    new Customer("Anne", "Frederiksen", new CPRNumber("170383-9313"));
            customer4 =    new Customer("Sofie","Andersen", new CPRNumber("120288-8383"));

            //adding all customers to the database
            allCustomers.put(customer1.getCprNumber().toString(),customer1);
            allCustomers.put(customer2.getCprNumber().toString(),customer2);
            allCustomers.put(customer3.getCprNumber().toString(),customer3);
            allCustomers.put(customer4.getCprNumber().toString(),customer4);
        } catch (InvalidCprException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Emilie (s153762)
     * @param customer an instance of the Customer class
     */
    public void addCustomer(Customer customer) {
        allCustomers.put(customer.getCprNumber().toString(), customer);
    }

    /**
     * @author Emilie (s153762)
     * @param cprNumber the CPR number of the customer
     * @return customer
     * @throws CustomerNotFoundException
     */
    public Customer getCustomer(CPRNumber cprNumber) throws CustomerNotFoundException {
        Customer customer = allCustomers.get(cprNumber.toString());
        if (customer == null){
            throw new CustomerNotFoundException(cprNumber.toString());
        }
        return customer;
    }

    /**
     * @author Emilie (s153762)
     * @return amount of customers in the database.
     */
    public int getAmountOfCustomers() {
        return allCustomers.size();
    }
}