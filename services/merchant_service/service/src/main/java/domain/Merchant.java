package domain;

/**
 * @author Emilie (s153762)
 */
public class Merchant {
    private String firstName, lastName;
    private CVRNumber cvr;

    /**
     * @author Emilie (s153762)
     * @param firstName the first name of the merchant
     * @param lastName the last name of the merchant
     * @param cvr the CVR number of the merchant
     */
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
