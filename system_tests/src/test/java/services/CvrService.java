package services;

import com.mifmif.common.regex.Generex;

/**
 * @author Sebastian (s144071)
 */
public class CvrService {
  public static String generateCvr() {
    String cprRegex = "DK[0-9]{8}";

    Generex generex = new Generex(cprRegex);

    return generex.random();
  }
}
