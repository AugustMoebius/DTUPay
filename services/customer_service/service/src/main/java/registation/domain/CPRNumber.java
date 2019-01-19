package registation.domain;

import com.sun.media.sound.InvalidFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPRNumber {

    private String cprNumber;

    /**
     * @author Esben Løvendal Kruse (s172986)
     * @param cprNumber
     * @throws InvalidFormatException
     */
    public CPRNumber(String cprNumber) throws InvalidFormatException {
        Pattern pattern =
                Pattern.compile(
                        "^(?:(?:31(?:0[13578]|1[02])|(?:30|29)(?:0[13-9]|1[0-2])|(?:0[1-9]|1[0-9]|2[0-8])(?:0[1-9]|1[0-2]))[0-9]{2}-?[0-9]|290200-?[4-9]|2902(?:(?!00)[02468][048]|[13579][26])-?[0-3])[0-9]{3}|000000-?0000$");

        Matcher matcher = pattern.matcher(cprNumber);

        if (matcher.matches()) {
            this.cprNumber = cprNumber;
        } else {
            throw new InvalidFormatException("The input CPRNumber is not the correct format");
        }
    }

    /**
     * @author Ebbe Berthold (s125015)
     * @return cprNumber
     */
    public String getCprNumber() {
        return cprNumber;
    }
}