package networking.rest_adapter.requests;

public class PaymentRequest {
  public String merchantId;
  public int paymentAmount;
  public String tokenId;

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
