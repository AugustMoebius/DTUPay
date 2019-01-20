package merchant.networking.requests;

/**
 * @author Sebastian
 */
public class RegisterMerchantRequest {
    private String firstName, lastName, cvr;

    public RegisterMerchantRequest(String firstName, String lastName, String cvr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCvr() {
        return cvr;
    }
}
