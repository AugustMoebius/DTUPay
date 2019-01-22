package management.domain;

public class Merchant {
    private String firstName, lastName;
    private CVRNumber cvr;

    public Merchant(String firstName, String lastName, CVRNumber cvr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvr = cvr;
    }

<<<<<<< HEAD
    public String getId() {
=======
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CVRNumber getCvr() {
>>>>>>> 639431f7462b12e78471b09c450d7df59c152442
        return cvr;
    }
}
