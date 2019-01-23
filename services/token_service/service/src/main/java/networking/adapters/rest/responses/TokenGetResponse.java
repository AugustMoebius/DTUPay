package networking.adapters.rest.responses;

import domain.CPRNumber;


public class TokenGetResponse {
    private String id;
    private String cprNumber;
    private String barcode;
    private boolean isUsed;

    /**
     * @author Esben (s172986)
     * @param id
     * @param cprNumber
     * @param barcode
     */
    public TokenGetResponse(String id, CPRNumber cprNumber, String barcode, boolean isUsed) {
        this.id = id;
        this.cprNumber = cprNumber.toString();
        this.barcode = barcode;
        this.isUsed = isUsed;
    }

    public TokenGetResponse() {}

    /**
     * @author Esben (s172986)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Esben (s172986)
     * @return
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Esben (s172986)
     * @return
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @author August (s144461)
     * @param used
     */
    public void setUsed(boolean used) {
        isUsed = used;
    }

    /**
     * @author August (s144461)
     * @return isUsed
     */
    public boolean isUsed() {
        return isUsed;
    }

    /**
     * @author August (s144461)
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @author Sebastian (s144071)
     * @param cprNumber
     */
    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    /**
     * @author August (s144461)
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
