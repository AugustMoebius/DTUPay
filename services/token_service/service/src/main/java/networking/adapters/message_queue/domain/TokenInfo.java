package networking.adapters.message_queue.domain;

public class TokenInfo {

    private String merchantId;
    private int paymentAmount;
    private String tokenId;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param merchantId
     * @param paymentAmount
     * @param tokenId
     */
    public TokenInfo(String merchantId, int paymentAmount, String tokenId) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return payment amount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return tokenId
     */
    public String getTokenId() {
        return tokenId;
    }
}
