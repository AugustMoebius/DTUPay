package networking.adapters.message_queue.domain;

import domain.CPRNumber;

/**
 * @author Ebbe s125015
 */
public class TokenInfoVerified {

    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;

    /**
     * @author Ebbe (s125015)
     * @param merchantId
     * @param paymentAmount
     * @param tokenId
     * @param cprNumber
     */
    public TokenInfoVerified(String merchantId, int paymentAmount, String tokenId, CPRNumber cprNumber) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = cprNumber.toString();
    }

    /**
     * @author Ebbe (s125015)
     * @return merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Ebbe (s125015)
     * @return payment amount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author Ebbe (s125015)
     * @return tokenId
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Ebbe (s125015)
     * @return CPR Number
     */
    public String getCprNumber() {
        return cprNumber;
    }
}
