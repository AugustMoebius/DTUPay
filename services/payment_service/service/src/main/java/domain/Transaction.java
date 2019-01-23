package domain;


/**
 * @author August (s144461), Sebastian (s144071)
 */
public class Transaction {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;
    private boolean isRefunded;

    /**
     * @author Sebastian (s144071)
     * @param merchantId the identification of the merchant
     * @param paymentAmount the payment amount
     * @param tokenId the identification of the token
     * @param cprNumber the CPR number of the customer
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
     * @param refunded the refund-state of a transaction
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
