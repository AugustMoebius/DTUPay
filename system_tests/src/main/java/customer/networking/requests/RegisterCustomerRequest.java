package customer.networking.requests;

public class RegisterCustomerRequest {
    private String firstName, lastName, cpr;

    /**
     *
     * @param firstName
     * @param lastName
     * @param cpr
     * @author Emilie
     */
    public RegisterCustomerRequest(String firstName, String lastName, String cpr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
    }

    /**
     * @author Emilie
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
     * @author Emilie
     * @return cpr
     */
    public String getCpr() {
        return cpr;
    }
}
