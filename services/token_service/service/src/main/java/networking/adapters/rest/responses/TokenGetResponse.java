package networking.adapters.rest.responses;

import domain.CPRNumber;


public class TokenGetResponse {
    private String id;
    private String cprNumber;
    private String barcode;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param id
     * @param cprNumber
     * @param barcode
     */
    public TokenGetResponse(String id, CPRNumber cprNumber, String barcode) {
        this.id = id;
        this.cprNumber = cprNumber.toString();
        this.barcode = barcode;
    }

    public TokenGetResponse() {}

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getBarcode() {
        return barcode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
