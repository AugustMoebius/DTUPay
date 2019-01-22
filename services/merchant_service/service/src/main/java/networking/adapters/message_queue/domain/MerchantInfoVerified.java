package networking.adapters.message_queue.domain;

/**
 * @autor Emilie
 */
public class MerchantInfoVerified {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    public MerchantInfoVerified(String merchantId, int paymentAmount, String tokenId, String customerId) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = customerId;
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
