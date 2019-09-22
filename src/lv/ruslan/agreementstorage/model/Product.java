package lv.ruslan.agreementstorage.model;

import lv.ruslan.agreementstorage.utility.FilesUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

  private Object parentObject;
  private String name;
  private BigDecimal price;
  private List<Product> products;

  public Product(String name, BigDecimal price, Agreement agreement) {
    this.parentObject = agreement;
    this.name = name;
    this.price = price;
    this.products = new ArrayList<Product>();
  }

  public Product(String name, BigDecimal price, Product product) {
    this.parentObject = product;
    this.name = name;
    this.price = price;
    this.products = new ArrayList<Product>();
  }

  public String getName() {
    return name;
  }

  private boolean hasAgreement() {
    return this.parentObject.getClass().equals(Agreement.class);
  }

  public Agreement getAgreement() {
    if(hasAgreement()) {
      return (Agreement) this.parentObject;
    } else {
      return null;
    }
  }

  public String toString() {
    return "name["+ this.name +"], price["+ this.price +"], parentObject["+ this.parentObject.getClass() +"]";
  }

  public String toFileString() {
    return this.name + FilesUtility.fileDirPath_delimiter + this.price;
  }

  public static Product fromFileString(String filesString, Agreement agreement) {
    String[] values = filesString.split(FilesUtility.fileDirPath_delimiter);
    return new Product(values[0], new BigDecimal(values[1]), agreement);
  }
}
