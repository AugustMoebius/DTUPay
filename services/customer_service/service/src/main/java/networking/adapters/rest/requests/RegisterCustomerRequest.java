package networking.adapters.rest.requests;

/**
 * @author Emilie (s153762)
 */
public class RegisterCustomerRequest {
    private String firstName, lastName, cpr;
    public RegisterCustomerRequest(){}

    /**
     * @param firstName the first name of the customer
     * @param lastName the first name of the customer
     * @param cpr the CPR number of the customer
     * @author Emilie (s153762)
     */
    public RegisterCustomerRequest(String firstName, String lastName, String cpr) {
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
     * @author Emilie (s153762)
     * @param firstName the first name of the customer
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @author Emilie (s153762)
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @author Emilie (s153762)
     * @param lastName the last name of the customer
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @author Emilie (s153762)
     * @return cpr
     */
    public String getCpr() {
        return cpr;
    }

    /**
     * @author Emilie (s153762)
     * @param cpr the CPR number of the customer
     */
    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}
