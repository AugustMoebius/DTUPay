package domain;

import exceptions.InvalidCprException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ebbe s125015, Esben 172986
 */
public class CPRNumber {
    private static final Pattern pattern = Pattern.compile("[0-9]{6}-[0-9]{4}");

    private String cprNumber;

    /**
     * @author Esben (s172986)
     * @param cprNumber
     * @throws InvalidCprException
     */
    public CPRNumber(String cprNumber) throws InvalidCprException {
        Matcher matcher = CPRNumber.pattern.matcher(cprNumber);
        if (!matcher.matches()) {
            throw new InvalidCprException("Illegal registation: Invalid CPR number. Recieved " + cprNumber + ".");
        }

        this.cprNumber = cprNumber;
    }

    /**
     * @author Ebbe (s125015)
     * @return cprNumber
     */
    public String toString() {
        return cprNumber;
    }
}