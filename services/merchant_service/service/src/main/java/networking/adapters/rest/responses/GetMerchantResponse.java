package networking.adapters.rest.responses;

public class GetMerchantResponse {
    private String firstName, lastName, cvr;

    public GetMerchantResponse(String firstName, String lastName, String cvr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }
}
