package registation;

import com.sun.media.sound.InvalidFormatException;
import data.InMemoryDataSource;
import registation.domain.CPRNumber;
import registation.domain.Customer;
import registation.exceptions.CustomerInvalidInformation;
import registation.exceptions.CustomerInvalidName;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegistration {
    private InMemoryDataSource data;
    private Customer customer;
    public CustomerRegistration(){
        data = InMemoryDataSource.getInstance();

    }

    public void addCustomer(String firstName, String lastName, CPRNumber cprNumber) throws CustomerInvalidName, CustomerInvalidInformation, InvalidFormatException {
        // Validate first and last name
        Pattern pattern = Pattern.compile("([a-zA-Z]+)(.*)");
        Matcher matcherFirstName = pattern.matcher(firstName);
        Matcher matcherLastName = pattern.matcher(lastName);

        if(matcherFirstName.matches() && matcherLastName.matches()){
            if(!matcherFirstName.group(2).isEmpty()){
                throw new CustomerInvalidName(firstName);
            }
            if(!matcherLastName.group(2).isEmpty()){
                throw new CustomerInvalidName(lastName);
            }
        }

        if(!matcherFirstName.matches() || !matcherLastName.matches()){
            throw new CustomerInvalidInformation("");
        }

        customer = new Customer(firstName, lastName, cprNumber);
        // Add customer to the database
        data.addCustomer(customer);
    }

    public Customer getCustomer(){
        return customer;
    }
}
