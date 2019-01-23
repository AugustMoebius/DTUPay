package registation.domain;

/**
 * @author Esben (s172986)
 */
public class Customer {

    private String firstName;
    private String lastName;
    private CPRNumber cprNumber;

    /**
     * @author Esben (s172986)
     * @param firstName the first name of a customer
     * @param lastName the last name of a customer
     * @param cprNumber the CPR number of the customer
     */
    public Customer(String firstName, String lastName, CPRNumber cprNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public CPRNumber getCprNumber() {
        return cprNumber;
    }
}