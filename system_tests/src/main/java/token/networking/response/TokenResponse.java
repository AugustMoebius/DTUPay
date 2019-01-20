package token.networking.response;

public class TokenResponse {

    private String tokenId;
    private String barcodePath;

    public TokenResponse(String tokenId, String barcodePath) {
        this.tokenId = tokenId;
        this.barcodePath = barcodePath;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getBarcodePath() {
        return barcodePath;
    }
}
