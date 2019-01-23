package networking.adapters.message_queue.domain;

/**
 * @author Emilie (s153762)
 */
public class MerchantInfoVerified {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    /**
     * @author Emilie (s153762)
     * @param merchantId the Id of the merchant
     * @param paymentAmount the payment amount
     * @param tokenId the id of the token
     * @param customerId the Id of the customer
     */
    public MerchantInfoVerified(String merchantId, int paymentAmount, String tokenId, String customerId) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = customerId;
    }

    /**
     * @author Emilie (s153762)
     * @return
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Emilie (s153762)
     * @return
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author Emilie (s153762)
     * @return
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Emilie (s153762)
     * @return
     */
    public String getCprNumber() {
        return cprNumber;
    }
}
