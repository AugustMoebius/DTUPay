package payment.networking.requests;

public class PaymentRequest {
  private String merchantId;
  private int paymentAmount;
  private String tokenId;

  /**
   * @author Emilie
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
   * @author August
   * @return
   */
  public String getMerchantId() {
    return merchantId;
  }

  /**
   * @author Sebastian
   * @return
   */
  public int getPaymentAmount() {
    return paymentAmount;
  }

  /**
   * @author August
   * @return
   */
  public String getTokenId() {
    return tokenId;
  }
}
