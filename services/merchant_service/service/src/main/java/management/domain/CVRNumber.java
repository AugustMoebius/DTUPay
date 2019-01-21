package management.domain;

import management.exceptions.InvalidCvrException;

public class CVRNumber {
    private String cvr;
    public CVRNumber(String cvr) throws InvalidCvrException {
        if(cvr.length() != 8 || !cvr.matches("(DK[0-9]{8})")){
            throw new InvalidCvrException("Illegal registation: Invalid CVR number. Recieved " + cvr + ".");
        }
        this.cvr = cvr;
    }

    public String getCvrNumber(){return cvr; }
}
