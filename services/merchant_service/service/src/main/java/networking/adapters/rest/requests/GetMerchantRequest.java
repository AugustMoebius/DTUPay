package networking.adapters.rest.requests;

/**
 * @author Sarah
 */
public class GetMerchantRequest {
    private final String cvr;

    /**
     * @param cvr
     */
    public GetMerchantRequest(String cvr) {
        this.cvr = cvr;
    }

    /**
     * @return cvr
     */
    public String getCvr() {
        return cvr;
    }
}