package networking.adapters.rest.requests;


/**
 * @author August
 */
public class RefundRequest {
    private String tokenId;

    public RefundRequest(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }
}