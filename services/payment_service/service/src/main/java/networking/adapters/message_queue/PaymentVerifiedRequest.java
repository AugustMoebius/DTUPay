package networking.adapters.message_queue;

/**
 * @author August
 */
public class PaymentVerifiedRequest {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

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
}
