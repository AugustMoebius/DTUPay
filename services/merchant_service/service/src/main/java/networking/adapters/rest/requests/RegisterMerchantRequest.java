package networking.adapters.rest.requests;

public class RegisterMerchantRequest {
    private String firstName, lastName, cvr;

    /**
     * @author Sarah (s153659)
     * @param firstName the first name of the merchant
     * @param lastName the last name of the merchant
     * @param cvr the CVR number of the merchant
     */
    public RegisterMerchantRequest(String firstName, String lastName, String cvr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

    public RegisterMerchantRequest() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public    String getFirstName() {return firstName; }

    public String getLastName() {
        return lastName;
    }

    public String getCvr() {
        return cvr;
    }
}
