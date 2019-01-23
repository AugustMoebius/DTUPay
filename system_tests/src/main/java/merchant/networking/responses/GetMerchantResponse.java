package merchant.networking.responses;

/**
 * @author Sarah
 */
public class GetMerchantResponse {
    private String firstName, lastName;
    private String cvr;

    public GetMerchantResponse(String firstName, String lastName, String cvr){
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
