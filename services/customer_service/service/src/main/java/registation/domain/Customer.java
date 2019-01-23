package registation.domain;

public class Customer {

    private String firstName;
    private String lastName;
    private CPRNumber cprNumber;

    /**
     * @author Esben (s172986)
     * @param firstName,
     * @param lastName,
     * @param cprNumber
     */
    public Customer(String firstName, String lastName, CPRNumber cprNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cprNumber = cprNumber;
    }

    /**
     * @author Ebbe (s125015)
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @author Sarah (s153659)
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @author Esben (s172986)
     * @return cprNumber
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }
}