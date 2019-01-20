package domain;

public class Transaction {
    private String merchantId;
    private int paymentAmount;
    private String tokenId;
    private String cprNumber;
    private boolean isRefunded;

    public Transaction(String merchantId, int paymentAmount, String tokenId, String cprNumber) {
        this.merchantId = merchantId;
        this.paymentAmount = paymentAmount;
        this.tokenId = tokenId;
        this.cprNumber = cprNumber;
        this.isRefunded = false;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public String getTokenId() {
        return tokenId;
    }

    public boolean isRefunded() {
        return isRefunded;
    }

    public void setRefunded(boolean refunded) {
        isRefunded = refunded;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCprNumber() {
        return cprNumber;
    }
}
