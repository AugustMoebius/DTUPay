package networking.adapters.rest.responses;

/**
 * @author Esben (s172986), Sebastian (s144071)
 */
public class TokenBarcodePair {

    private String tokenId;
    private String barcodeRelativePath;

    /**
     * @author Esben (s172986)
     * @param tokenId
     * @param barcodeRelativePath
     */
    public TokenBarcodePair(String tokenId, String barcodeRelativePath) {
        this.tokenId = tokenId;
        this.barcodeRelativePath = barcodeRelativePath;
    }

    /**
     * @author Sebastian (s144071)
     */
    public TokenBarcodePair() {}

    /**
     * @author Esben (s172986)
     * @return
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Sebastian (s144071)
     * @return
     */
    public String getBarcodeRelativePath() {
        return barcodeRelativePath;
    }

    /**
     * @author Esben (s172986)
     * @param tokenId
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * @author Sebastian (s144071)
     * @param barcodeRelativePath
     */
    public void setBarcodeRelativePath(String barcodeRelativePath) {
        this.barcodeRelativePath = barcodeRelativePath;
    }
}
