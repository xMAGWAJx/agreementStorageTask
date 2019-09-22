package lv.ruslan.agreementstorage.utility;

import lv.ruslan.agreementstorage.model.Agreement;
import lv.ruslan.agreementstorage.model.Product;

public class ValidateUtility {
  public static boolean isValidProduct(Product product, String agreementName) {
    Agreement productAgreement = product.getAgreement();
    if(productAgreement == null) {
      System.out.println("ValidateUtility : isValidProduct : WARNING: Invalid product ["+ product.getName() + "] - has no agreement.");
      return false;
    }
    if (!productAgreement.getName().equalsIgnoreCase(agreementName)) {
      System.out.println("ValidateUtility : isValidProduct : WARNING: Invalid product ["+ product.getName() + "] - has foreign agreement.");
      return false;
    }
    return true;
  }
}
