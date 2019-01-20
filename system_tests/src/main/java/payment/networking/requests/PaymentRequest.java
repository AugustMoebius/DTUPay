package payment.networking.requests;

public class PaymentRequest {
  private String merchantId;
  private int paymentAmount;
  private String tokenId;

  public PaymentRequest(String merchantId, int paymentAmount, String tokenId) {
    this.merchantId = merchantId;
    this.paymentAmount = paymentAmount;
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
