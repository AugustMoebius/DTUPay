package networking.adapters.message_queue;

/**
 * @author August (s144461)
 */
public class PaymentVerifiedRequest {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    /**
     * @author August (s144461)
     * @param merchantId the identification of the merchant
     * @param paymentAmount the payment amount
     * @param tokenId the identification of the token
     * @param cprNumber the CPR number
     */
    public PaymentVerifiedRequest(String merchantId, int paymentAmount, String tokenId, String cprNumber) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = cprNumber;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public String getTokenId() {
        return tokenId;
    }
}
