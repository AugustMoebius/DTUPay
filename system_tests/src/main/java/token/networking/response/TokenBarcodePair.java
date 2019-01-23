package token.networking.response;

public class TokenBarcodePair {

    private String tokenId;
    private String barcodeRelativePath;

    /**
     * @author August
     * @param tokenId
     * @param barcodeRelativePath
     */
    public TokenBarcodePair(String tokenId, String barcodeRelativePath) {
        this.tokenId = tokenId;
        this.barcodeRelativePath = barcodeRelativePath;
    }

    /**
     * @author Sebastian
     */
    public TokenBarcodePair() {}

    /**
     * @author Sebastian
     * @return token id
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author August
     * @param tokenId
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * @author Sebastian
     * @return path for barcode
     */
    public String getBarcodeRelativePath() {
        return barcodeRelativePath;
    }

    /**
     * @author August
     * @param barcodeRelativePath
     */
    public void setBarcodeRelativePath(String barcodeRelativePath) {
        this.barcodeRelativePath = barcodeRelativePath;
    }
}