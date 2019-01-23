package token.networking.requests;

/**
 * @author Esben (s172986)
 */
public class TokenRequest {
    private String cprNumber;
    private int numberOfTokens;

    /**
     * @author Esben (s172986)
     * @param cprNumber
     * @param numberOfTokens
     */
    public TokenRequest(String cprNumber, int numberOfTokens) {
        this.cprNumber = cprNumber;
        this.numberOfTokens = numberOfTokens;
    }

    /**
     * @author Esben (s172986)
     * @return cpr number
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Esben (s172986)
     * @return amount of tokens
     */
    public int getNumberOfTokens() {
        return numberOfTokens;
    }
}
