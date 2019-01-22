package merchant.networking.requests;

public class GetMerchantRequest {
    private final String cvr;

    public GetMerchantRequest(String cvr) {
        this.cvr = cvr;
    }

    public String getCvr() {
        return cvr;
    }
}
