package token.networking.response;

import java.util.List;

/**
 * @author Esben (s172986), Sebastian (s144071)
 */
public class TokenGeneratedResponse {

    private List<TokenBarcodePair> tokenBarcodePairs;

    /**
     * @author Esben (s172986)
     * @param tokenBarcodePairs
     */
    public TokenGeneratedResponse(List<TokenBarcodePair> tokenBarcodePairs) {
        this.tokenBarcodePairs = tokenBarcodePairs;
    }

    /**
     * @author Sebastian (s144071)
     */
    public TokenGeneratedResponse() {}

    /**
     * @author Esben (s172986)
     * @return token barcode pair
     */
    public List<TokenBarcodePair> getTokenBarcodePairs() {
        return tokenBarcodePairs;
    }

    /**
     * @author Sebastian (s144071)
     * @param tokenBarcodePairs
     */
    public void setTokenBarcodePairs(List<TokenBarcodePair> tokenBarcodePairs) {
        this.tokenBarcodePairs = tokenBarcodePairs;
    }
}

