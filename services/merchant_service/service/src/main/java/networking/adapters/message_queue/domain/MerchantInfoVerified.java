package networking.adapters.message_queue.domain;

public class MerchantInfoVerified {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    /**
     * @author Emilie
     * @param merchantId
     * @param paymentAmount
     * @param tokenId
     * @param customerId
     */
    public MerchantInfoVerified(String merchantId, int paymentAmount, String tokenId, String customerId) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = customerId;
    }

    /**
     * @author Emilie
     * @return
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Emilie
     * @return
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author Emilie
     * @return
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Emilie
     * @return
     */
    public String getCprNumber() {
        return cprNumber;
    }
}
