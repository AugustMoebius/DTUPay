package networking.adapters.message_queue.domain;

/**
 * @author Ëmilie
 */
public class PaymentInitializedRequest {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    public PaymentInitializedRequest(){
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

    public String getTokenId() {
        return tokenId;
    }

    public String getCprNumber() {
        return cprNumber;
    }
}
