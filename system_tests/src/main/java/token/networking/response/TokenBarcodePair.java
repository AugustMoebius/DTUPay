package token.networking.response;

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
     * @author Sebastian (s144071)
     * @return token id
     */
    public String getTokenId() {
        return tokenId;
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
     * @return path for barcode
     */
    public String getBarcodeRelativePath() {
        return barcodeRelativePath;
    }

    /**
     * @author Esben (s172986)
     * @param barcodeRelativePath
     */
    public void setBarcodeRelativePath(String barcodeRelativePath) {
        this.barcodeRelativePath = barcodeRelativePath;
    }
}