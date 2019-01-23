package domain;

import java.util.UUID;

/**
 * @author Sebastian s144071, Esben s172986, Ebbe s125015, August s144461
 */
public class Token {
    private String id;
    private CPRNumber cprNumber;
    private boolean isUsed;
    private String barcodeFileName;

    /**
     * @author Ebbe (s125015)
     * @param id
     * @param cprNumber
     */
    public Token(String id, CPRNumber cprNumber) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.isUsed = false;
    }

    /**
     * @author Esben (s172986)
     * @param cprNumber
     */
    public Token(CPRNumber cprNumber) {
        this.cprNumber = cprNumber;
        this.id = UUID.randomUUID().toString();
        this.isUsed = false;
    }

    public Token() {}

    /**
     * @author Esben (s172986)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Ebbe (s125015)
     * @return CPRNumber
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }

    /**
     * @author August (s144461)
     * @return
     */
    public String getBarcodeFileName() {
        return barcodeFileName;
    }

    /**
     * @author Sebastian (s144071)
     * @param barcode
     */
    public void setBarcodeFileName(String barcode) {
        this.barcodeFileName = barcode;
    }

    /**
     * @author August (s144461)
     * @return
     */
    public boolean isUsed() { return isUsed; }

    /**
     * @author Sebastian (s144071)
     * @param used
     */
    public void setUsed(boolean used) {
        isUsed = used;
    }
}
