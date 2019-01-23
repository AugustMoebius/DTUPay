package domain;

public class Transaction {
    private String timestamp;
    private int amount;
    private String customerID;
    private String merchantID;

    /**
     * @author Sarah (s153659)
     * @param timestamp
     * @param amount
     * @param customerID
     * @param merchantID
     */
    public Transaction(String timestamp, int amount, String customerID, String merchantID){
        this.timestamp = timestamp;
        this.amount = amount;
        this.customerID = customerID;
        this.merchantID = merchantID;
    }

}
