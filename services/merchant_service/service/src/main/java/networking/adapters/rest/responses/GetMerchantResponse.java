package networking.adapters.rest.responses;

public class GetMerchantResponse {
    private String firstName, lastName, cvr;

    public GetMerchantResponse(String firstName, String lastName, String cvr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }
}
