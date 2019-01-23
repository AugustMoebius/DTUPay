package networking.adapters.rest.requests;

/**
 * @author Sarah (s153659)
 */
public class GetMerchantRequest {
    private final String cvr;

    public GetMerchantRequest(String cvr) {
        this.cvr = cvr;
    }

    public String getCvr() {
        return cvr;
    }
}