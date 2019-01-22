package management.domain;

public class Merchant {
    private String firstName, lastName;
    private CVRNumber cvr;

    public Merchant(String firstName, String lastName, CVRNumber cvr){
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

    public CVRNumber getCvr() {
        return cvr;
    }
}
