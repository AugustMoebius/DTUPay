package domain;

import java.util.UUID;

public class Token {
    private String id;
    private CPRNumber cprNumber;
    private String barcodePath;

    /**
     * @author Ebbe Berthold (s125015)
     * @param id
     * @param cprNumber
     */
    public Token(String id, CPRNumber cprNumber) {
        this.id = id;
        this.cprNumber = cprNumber;
        this.barcodePath = id + ".png";
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     */
    public Token(CPRNumber cprNumber) {
        this.cprNumber = cprNumber;
        this.id = UUID.randomUUID().toString();
        this.barcodePath = id + ".png";
    }

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return CPRNumber
     */
    public CPRNumber getCprNumber() {
        return cprNumber;
    }

    public String getBarcodePath() {
        return barcodePath;
    }

}
