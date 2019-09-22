package lv.ruslan.agreementstorage.utility;

import lv.ruslan.agreementstorage.model.Agreement;
import lv.ruslan.agreementstorage.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesUtility {

  public static final String fileDirPath = ".\\agreementFiles\\";
  public static final String fileDirPath_delimiter = " - ";

  public static String createAgreement(Agreement agreement) throws Exception {
    String filePathToSave =fileDirPath+(agreement.getName())+".txt";
    StringBuilder sb = new StringBuilder();
    // First line: Agreement data
    sb.append(agreement.toFileString() + "\n");
    // Second line and further: Agreement product list
    for(Product product : agreement.getProducts()) {
      sb.append(product.toFileString() + "\n");
    }

    // Save data to a new file
    writeTextToFile(sb.toString(), filePathToSave);
    System.out.println("FilesUtility : saveAgreement: SUCCESS - saved to file ["+ filePathToSave +"]");
    return filePathToSave;
  }

  public static Agreement readAgreement(String filePath) throws Exception {
    String textFromFile = getTextFromFile(filePath);
    List<String> linesList = multiLineTestToArray(textFromFile);
    Agreement agreementLoaded = Agreement.fromFileString(linesList.get(0));

    for(int i = 1; i < linesList.size(); i++) {
      Product productLoaded = Product.fromFileString(linesList.get(i), agreementLoaded);
      agreementLoaded.addProduct(productLoaded);
    }

    System.out.println("FilesUtility : loadAgreement : LOADED ["+ agreementLoaded.getName() +"]");
    return agreementLoaded;
  }

  private static void writeTextToFile(String text, String path) throws Exception {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(path));
      writer.write(text);
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        throw new FileNotFoundException();
      }
    }
  }

  private static String getTextFromFile(String filePath) throws Exception {
    StringBuffer result = new StringBuffer();
    FileInputStream fis = new FileInputStream(filePath);
    BufferedReader myInput = new BufferedReader(new InputStreamReader(fis));
    String thisLine;
    while ((thisLine = myInput.readLine()) != null) {
      result.append(thisLine + "\n");
    }
    fis.close();
    return result.toString();
  }

  public static ArrayList<String> multiLineTestToArray(String text) throws Exception {
    ArrayList<String> textLines = new ArrayList<>();
    InputStream ins = new ByteArrayInputStream(text.getBytes());
    BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
    String strLine;
    while ((strLine = br.readLine()) != null) {
      textLines.add(strLine);
    }
    br.close();
    return textLines;
  }
}
