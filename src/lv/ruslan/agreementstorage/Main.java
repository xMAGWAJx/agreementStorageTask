package lv.ruslan.agreementstorage;

import lv.ruslan.agreementstorage.api.AgreementStorageApi;
import lv.ruslan.agreementstorage.model.Agreement;
import lv.ruslan.agreementstorage.model.Product;

import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    try {
      // Generates agreement data.
      Agreement agreementToSave = createAgreement();

      // Saves agreement to target location using API
      String savedAgreementFilePath = AgreementStorageApi.save(agreementToSave);

      // Load agreement from target location using API
      Agreement agreementLoaded = AgreementStorageApi.load(savedAgreementFilePath);

      // Result
      System.out.println("Main : agreementLoaded from fileString: " + agreementLoaded.toString());

    } catch (Exception exception) {
      System.out.println("Main : Something went wrong. ");
      exception.printStackTrace();
    }
  }

  private static Agreement createAgreement() {
    // Signed by
    Agreement agreementToSave = new Agreement("Ruslan");

    // Product 1
    Product product1 = new Product("product1", new BigDecimal("5.00"), agreementToSave);
    System.out.println("Main : Product created: " + product1.toString());

    // Product 2
    Product product2 = new Product("product2", new BigDecimal("10.00"), agreementToSave);
    System.out.println("Main : Product created: " + product2.toString());

    // Product 3 - test for invalid product
    Product product3 = new Product("product3", new BigDecimal("15.00"), product2);
    System.out.println("Main : Product created: " + product3.toString());

    // Product 4
    Product product4 = new Product("product4", new BigDecimal("30.00"), agreementToSave);
    System.out.println("Main : Product created: " + product4.toString());

    // Add products to agreement
    agreementToSave.addProduct(product1);
    agreementToSave.addProduct(product2);
    agreementToSave.addProduct(product3);
    agreementToSave.addProduct(product4);

    System.out.println("Main : agreementToSave ready for saving: " + agreementToSave.toString());
    return agreementToSave;
  }

}
