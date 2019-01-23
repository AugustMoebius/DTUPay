package networking.adapters.rest.requests;

public class RegisterMerchantRequest {
    private String firstName, lastName, cvr;

    /**
     * @author Sarah (s153659)
     * @param firstName
     * @param lastName
     * @param cvr
     */
    public RegisterMerchantRequest(String firstName, String lastName, String cvr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

    /**
     * @author Sarag
     */
    public RegisterMerchantRequest() {}

    /**
     * @author Sarah (s153659)
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @author
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @author
     * @param cvr
     */
    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    /**
     * @author
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
     * @author Sarah (s153659)
     * @return cvr
     */
    public String getCvr() {
        return cvr;
    }
}
