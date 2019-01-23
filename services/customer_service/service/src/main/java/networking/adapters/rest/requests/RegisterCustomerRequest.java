package networking.adapters.rest.requests;

public class RegisterCustomerRequest {
    private String firstName, lastName, cpr;
    public RegisterCustomerRequest(){}

    /**
     * @param firstName
     * @param lastName
     * @param cpr
     * @author Emilie
     */
    public RegisterCustomerRequest(String firstName, String lastName, String cpr) {
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
     * @author Emilie
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @author Emilie
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @author Emilie
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @author Emilie
     * @return cpr
     */
    public String getCpr() {
        return cpr;
    }

    /**
     * @author Emilie
     * @param cpr
     */
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}
