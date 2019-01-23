package token.networking.response;

public class TokenResponse {
    private String id;
    private String cprNumber;
    private String barcode;
    private boolean used;

    public TokenResponse(){

    }

    /**
     * @author Ebbe
     * @param id
     * @param cprNumber
     * @param barcode
     * @param used
     */
    public TokenResponse(String id, String cprNumber, String barcode, boolean used) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.barcode = barcode;
        this.used = used;
    }

    /**
     * @author Ebbe
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @author Ebbe
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @author Ebbe
     * @return cpr number
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Ebbe
     * @param cprNumber
     */
    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    /**
     * @author Ebbe
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @author Ebbe
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @author Ebbe
     * @return isUsed
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @author Ebbe
     * @param used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }
}

