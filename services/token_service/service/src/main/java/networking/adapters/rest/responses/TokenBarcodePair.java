package networking.adapters.rest.responses;

public class TokenBarcodePair {

    private String tokenId;
    private String barcodeRelativePath;

    public TokenBarcodePair(String tokenId, String barcodeRelativePath) {
        this.tokenId = tokenId;
        this.barcodeRelativePath = barcodeRelativePath;
    }

    public TokenBarcodePair() {}

    public String getTokenId() {
        return tokenId;
    }

    public String getBarcodeRelativePath() {
        return barcodeRelativePath;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setBarcodeRelativePath(String barcodeRelativePath) {
        this.barcodeRelativePath = barcodeRelativePath;
    }
}