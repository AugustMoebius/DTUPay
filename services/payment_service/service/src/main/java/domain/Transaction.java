package domain;


/**
 * @author August
 */
public class Transaction {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;
    private boolean isRefunded;

    /**
     * @author Sebastian
     * @param merchantId
     * @param paymentAmount
     * @param tokenId
     * @param cprNumber
     */
    public Transaction(String merchantId, int paymentAmount, String tokenId, String cprNumber) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = cprNumber;
        this.isRefunded = false;
    }

    /**
     * @author Sebastian
     * @return payment amount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author August
     * @return token id
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author August
     * @return isRefunded
     */
    public boolean isRefunded() {
        return isRefunded;
    }

    /**
     * @author Sebastian
     * @param refunded
     */
    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    /**
     * @author August
     * @return merchant id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Sebastian
     * @return cpr number
     */
    public String getCprNumber() {
        return cprNumber;
    }
}
