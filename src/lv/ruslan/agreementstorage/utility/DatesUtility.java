package lv.ruslan.agreementstorage.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class DatesUtility {
  public static String currentDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    // According to task I should use this format day/month/year but it's possible
    // to use symbols like / \ etc. in windows for files .So I will use dot (.).
    return dateFormat.format(date);
  }
}
