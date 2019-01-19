package registation.domain;

public class Customer {

    private String firstName;
    private String lastName;
    private CPRNumber cprNumber;

    /**
     * @author Esben Løvendal Kruse (s172986)
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
     * @author Ebbe Berthold (s125015)
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @author Sarah
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return cprNumber
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }
}