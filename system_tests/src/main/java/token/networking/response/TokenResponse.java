package token.networking.response;

/**
 * @author Ebbe (s125015)
 */
public class TokenResponse {
    private String id;
    private String cprNumber;
    private String barcode;
    private boolean used;

    public TokenResponse(){

    }

    /**
     * @author Ebbe (s125015)
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
     * @author Ebbe (s125015)
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @author Ebbe (s125015)
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @author Ebbe (s125015)
     * @return cpr number
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Ebbe (s125015)
     * @param cprNumber
     */
    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    /**
     * @author Ebbe (s125015)
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @author Ebbe (s125015)
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * @author Ebbe (s125015)
     * @return isUsed
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @author Ebbe (s125015)
     * @param used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }
}

