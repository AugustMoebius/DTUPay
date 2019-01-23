package payment.networking.requests;

/**
 * @author Emilie (s153762)
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
