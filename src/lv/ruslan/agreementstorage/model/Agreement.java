package lv.ruslan.agreementstorage.model;

import lv.ruslan.agreementstorage.utility.DatesUtility;
import lv.ruslan.agreementstorage.utility.FilesUtility;
import lv.ruslan.agreementstorage.utility.ValidateUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agreement {

  private String name;
  private String signedBy;
  private List<Product> products;

  private static String createAgreementName() {
    return "Agreement " + DatesUtility.currentDate(new Date());
  }

  public Agreement (String signedBy) {
    this.name = createAgreementName();
    this.signedBy = signedBy;
    this.products = new ArrayList<Product>();
  }

  public Agreement (String name, String signedBy) {
    this.name = name;
    this.signedBy = signedBy;
    this.products = new ArrayList<Product>();
  }

  public String getName() {
    return name;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void addProduct(Product product) {
    if (ValidateUtility.isValidProduct(product, this.name)){
      this.products.add(product);
    } else {
      System.out.println("Agreement : addProduct : WARNING: Product ["+product.getName()+"] - not added.");
    }
  }

  public String toString() {
    return "name["+ this.name +"], signedBy["+ this.signedBy +"], products in list["+ products.size() +"]";
  }

  public String toFileString() {
    return this.name + FilesUtility.fileDirPath_delimiter + this.signedBy;
  }

  public static Agreement fromFileString(String fileString) {
    String[] values = fileString.split(FilesUtility.fileDirPath_delimiter);
    return new Agreement(values[0], values[1]);
  }

}
