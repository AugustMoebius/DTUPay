package networking.adapters.rest.response;

import domain.Token;

import java.util.List;

public class TokenResponse {

    private List<String> tokenIds;
    private List<String> barcodes;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenIds
     * @param barcodes
     */
    public TokenResponse(List<String> tokenIds, List<String> barcodes) {
        this.tokenIds = tokenIds;
        this.barcodes = barcodes;
    }

    public TokenResponse() {}

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public List<String> getTokenIds() {
        return tokenIds;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public List<String> getBarcodes() {
        return barcodes;
    }
}

