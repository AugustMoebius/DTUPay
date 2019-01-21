package networking.adapters.rest.responses;

import java.util.List;

public class TokenGeneratedResponse {

    private List<String> tokenIds;
    private List<String> barcodes;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param tokenIds
     * @param barcodes
     */
    public TokenGeneratedResponse(List<String> tokenIds, List<String> barcodes) {
        this.tokenIds = tokenIds;
        this.barcodes = barcodes;
    }

    public TokenGeneratedResponse() {}

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

