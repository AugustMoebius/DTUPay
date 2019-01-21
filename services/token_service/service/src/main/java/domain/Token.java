package domain;

import java.util.UUID;


public class Token {
    private String id;
    private CPRNumber cprNumber;
    private boolean isUnused;
    private String barcodeFileName;

    /**
     * @author Ebbe Berthold (s125015)
     * @param id
     * @param cprNumber
     */
    public Token(String id, CPRNumber cprNumber) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.isUnused = true;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     */
    public Token(CPRNumber cprNumber) {
        this.cprNumber = cprNumber;
        this.id = UUID.randomUUID().toString();
    }

    public Token() {}

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return CPRNumber
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }

    public String getBarcodeFileName() {
        return barcodeFileName;
    }

    public void setBarcodeFileName(String barcode) {
        this.barcodeFileName = barcode;
    }

    public boolean getIsUnused() { return isUnused;
    }
    public void setIsUnused(boolean state) { this.isUnused = state; }
}
