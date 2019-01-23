package payment.networking.requests;

/**
 * @author Emilie (s153762), August (s144461), Sebastian (s144071)
 */
public class PaymentRequest {
  private String merchantId;
  private int paymentAmount;
  private String tokenId;

  /**
   * @author Emilie (s153762)
   * @param merchantId
   * @param paymentAmount
   * @param tokenId
   */
  public PaymentRequest(String merchantId, int paymentAmount, String tokenId) {
    this.merchantId = merchantId;
    this.paymentAmount = paymentAmount;
    this.tokenId = tokenId;
  }

  /**
   * @author August (s144461)
   * @return
   */
  public String getMerchantId() {
    return merchantId;
  }

  /**
   * @author Sebastian (s144071)
   * @return
   */
  public int getPaymentAmount() {
    return paymentAmount;
  }

  /**
   * @author August (s144461)
   * @return
   */
  public String getTokenId() {
    return tokenId;
  }
}
