package lv.ruslan.agreementstorage.api;

import lv.ruslan.agreementstorage.utility.FilesUtility;
import lv.ruslan.agreementstorage.model.Agreement;

public class AgreementStorageApi {
  public static String save(Agreement agreement) throws Exception {
    return FilesUtility.createAgreement(agreement);
  }

  public static Agreement load(String filePath) throws Exception {
    return FilesUtility.readAgreement(filePath);
  }
}
