package services;

import com.mifmif.common.regex.Generex;

public class CvrService {
  public static String generateCvr() {
    String cprRegex = "DK[0-9]{8}";

    Generex generex = new Generex(cprRegex);

    return generex.random();
  }
}
