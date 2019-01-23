package domain;

/**
 * @author Sarah (s153659)
 */
public class Transaction {
    private String timestamp;
    private int amount;
    private String customerID;
    private String merchantID;

    /**
     * @author Sarah (s153659)
     * @param timestamp timestamp for when the transaction has happened
     * @param amount the price of the product bought
     * @param customerID the Id of the customer
     * @param merchantID the Id of the merchant
     */
    public Transaction(String timestamp, int amount, String customerID, String merchantID){
        this.timestamp = timestamp;
        this.amount = amount;
        this.customerID = customerID;
        this.merchantID = merchantID;
    }

}
