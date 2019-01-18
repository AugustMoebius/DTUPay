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

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}
