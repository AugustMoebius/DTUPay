package services;

import com.mifmif.common.regex.Generex;

/**
 * @author Sebastian (s144071)
 */
public class CprService {
  public static String generateCpr() {
    String cprRegex = "[0-9]{6}-[0-9]{4}";

    Generex generex = new Generex(cprRegex);

    return generex.random();
  }
}
