package domain;


/**
 * @author August (s144461)
 */
public class Transaction {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;
    private boolean isRefunded;

    /**
     * @author Sebastian (s144071)
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
     * @author Sebastian (s144071)
     * @return payment amount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author August (s144461)
     * @return token id
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author August (s144461)
     * @return isRefunded
     */
    public boolean isRefunded() {
        return isRefunded;
    }

    /**
     * @author Sebastian (s144071)
     * @param refunded
     */
    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    /**
     * @author August (s144461)
     * @return merchant id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Sebastian (s144071)
     * @return cpr number
     */
    public String getCprNumber() {
        return cprNumber;
    }
}
