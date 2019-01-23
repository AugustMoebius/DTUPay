package domain;

import exceptions.InvalidCvrException;

/**
 * @author Sarah
 */
public class CVRNumber {
    private String cvr;
    public CVRNumber(String cvr) throws InvalidCvrException {
        if(cvr.length() != 10 || !cvr.matches("(DK[0-9]{8})")){
            throw new InvalidCvrException(cvr);
        }
        this.cvr = cvr;
    }

    public String getCvrNumber(){return cvr; }
}
