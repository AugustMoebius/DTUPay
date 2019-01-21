package domain;

import exceptions.InvalidCprException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPRNumber {
    private static final Pattern pattern = Pattern.compile("^(?:(?:31(?:0[13578]|1[02])|(?:30|29)(?:0[13-9]|1[0-2])|(?:0[1-9]|1[0-9]|2[0-8])(?:0[1-9]|1[0-2]))[0-9]{2}-?[0-9]|290200-?[4-9]|2902(?:(?!00)[02468][048]|[13579][26])-?[0-3])[0-9]{3}|000000-?0000$");

    private String cprNumber;

    /**
     * @author Esben Løvendal Kruse (s172986)
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
     * @author Ebbe Berthold (s125015)
     * @return cprNumber
     */
    public String toString() {
        return cprNumber;
    }
}