package networking.adapters.rest.responses;

public class GetMerchantResponse {
    private String firstName, lastName, cvr;

    /**
     * @author Sarah
     * @param firstName
     * @param lastName
     * @param cvr
     */
    public GetMerchantResponse(String firstName, String lastName, String cvr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

    /**
     * @author Sarah
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @author Sarah
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @author Sarah
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @author Sarah
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @author Sarah
     * @return cvr
     */
    public String getCvr() {
        return cvr;
    }

    /**
     * @author Sarah
     * @param cvr
     */
    public void setCvr(String cvr) {
        this.cvr = cvr;
    }
}
