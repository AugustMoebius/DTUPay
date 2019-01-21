package networking.adapters.rest.responses;

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

    public TokenGeneratedResponse() {}

    public List<TokenBarcodePair> getTokenBarcodePairs() {
        return tokenBarcodePairs;
    }

    public void setTokenBarcodePairs(List<TokenBarcodePair> tokenBarcodePairs) {
        this.tokenBarcodePairs = tokenBarcodePairs;
    }
}

