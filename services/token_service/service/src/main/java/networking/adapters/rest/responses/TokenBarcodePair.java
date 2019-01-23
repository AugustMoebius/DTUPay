package networking.adapters.rest.responses;

public class TokenBarcodePair {

    private String tokenId;
    private String barcodeRelativePath;

    /**
     * @author August s144461
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
     * @author August
     * @return
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @author Sebastian
     * @return
     */
    public String getBarcodeRelativePath() {
        return barcodeRelativePath;
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
     * @param barcodeRelativePath
     */
    public void setBarcodeRelativePath(String barcodeRelativePath) {
        this.barcodeRelativePath = barcodeRelativePath;
    }
}
