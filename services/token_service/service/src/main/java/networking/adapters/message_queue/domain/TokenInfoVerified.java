package networking.adapters.message_queue.domain;

import domain.CPRNumber;

public class TokenInfoVerified {

    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private CPRNumber cprNumber;

    /**
     * @author Ebbe Berthold (s125015)
     * @param merchantId
     * @param paymentAmount
     * @param tokenId
     * @param cprNumber
     */
    public TokenInfoVerified(String merchantId, int paymentAmount, String tokenId, CPRNumber cprNumber) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = cprNumber;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return payment amount
     */
    public int getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return tokenId
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return CPR Number
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }
}
