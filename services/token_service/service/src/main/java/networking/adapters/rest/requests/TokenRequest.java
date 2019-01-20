package networking.adapters.rest.requests;

public class TokenRequest {

    private int count;
    private String cprNumber;

    public TokenRequest(int count, String cprNumber) {
        this.count = count;
        this.cprNumber = cprNumber;
    }

    public int getCount() {
        return count;
    }

    public String getCprNumber() {
        return cprNumber;
    }
}