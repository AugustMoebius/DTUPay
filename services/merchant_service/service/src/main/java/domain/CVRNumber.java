package domain;

import exceptions.InvalidCvrException;

/**
 * @author Sarah (s153659)
 */
public class CVRNumber {
    private String cvr;

    /**
     * Sarah (s153659)
     * @param cvr the CVR number of the merchant
     * @throws InvalidCvrException
     */
    public CVRNumber(String cvr) throws InvalidCvrException {
        if(cvr.length() != 10 || !cvr.matches("(DK[0-9]{8})")){
            throw new InvalidCvrException(cvr);
        }
        this.cvr = cvr;
    }

    public String getCvrNumber(){return cvr; }
}
