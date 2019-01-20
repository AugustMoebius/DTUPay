package token.networking.requests;

public class TokenRequest {
    private String cprNumber;
    private int numberOfTokens;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     * @param numberOfTokens
     */
    public TokenRequest(String cprNumber, int numberOfTokens) {
        this.cprNumber = cprNumber;
        this.numberOfTokens = numberOfTokens;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getCprNumber() {
        return cprNumber;
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public int getNumberOfTokens() {
        return numberOfTokens;
    }
}
