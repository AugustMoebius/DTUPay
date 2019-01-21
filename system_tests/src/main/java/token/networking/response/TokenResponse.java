package token.networking.response;

/**
 * @author Ebbe
 */
public class TokenResponse {
    private String id;
    private String cprNumber;
    private String barcode;
    private boolean used;

    public TokenResponse(){

    }

    public TokenResponse(String id, String cprNumber, String barcode, boolean used) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.barcode = barcode;
        this.used = used;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}

