package networking.adapters.rest.requests;

/**
 * @author Sebastian (s144071)
 */
public class PaymentRequest {
  private String merchantId;
  private int paymentAmount;
  private String tokenId;

  /**
   * @author Sebastian (s144071)
   * @param merchantId the identification of the merchant
   * @param paymentAmount the payment amount
   * @param tokenId the identification of the token
   */
  public PaymentRequest(String merchantId, int paymentAmount, String tokenId) {
    this.merchantId = merchantId;
    this.paymentAmount = paymentAmount;
    this.tokenId = tokenId;
  }

  public PaymentRequest() {}

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public void setPaymentAmount(int paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
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
}
