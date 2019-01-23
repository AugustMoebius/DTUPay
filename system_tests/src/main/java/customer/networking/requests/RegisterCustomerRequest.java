package customer.networking.requests;

/**
 * @author Sarah (s153659), Emilie (s153762)
 */
public class RegisterCustomerRequest {
    private String firstName, lastName, cpr;

    /**
     * @author Sarah (s153659)
     * @param firstName
     * @param lastName
     * @param cpr
     */
    public RegisterCustomerRequest(String firstName, String lastName, String cpr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpr = cpr;
    }

    /**
     * @author Emilie (s153762)
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
     * @author Emilie (s153762)
     * @return cpr
     */
    public String getCpr() {
        return cpr;
    }
}
