package management.domain;

public class Merchant {
    private String companyName;
    private String cvr;

    public Merchant(String companyName, String cvr){
        this.companyName = companyName;
        this.cvr = cvr;
    }

    public String getId() {
        return cvr;
    }
}
