package token.networking.response;

import java.util.List;

public class TokenGeneratedResponse {

    private List<TokenBarcodePair> tokenBarcodePairs;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenBarcodePairs
     */
    public TokenGeneratedResponse(List<TokenBarcodePair> tokenBarcodePairs) {
        this.tokenBarcodePairs = tokenBarcodePairs;
    }

    /**
     * @author Sebastian
     */
    public TokenGeneratedResponse() {}

    /**
     * @author August
     * @return token barcode pair
     */
    public List<TokenBarcodePair> getTokenBarcodePairs() {
        return tokenBarcodePairs;
    }

    /**
     * @author Sebastian
     * @param tokenBarcodePairs
     */
    public void setTokenBarcodePairs(List<TokenBarcodePair> tokenBarcodePairs) {
        this.tokenBarcodePairs = tokenBarcodePairs;
    }
}

