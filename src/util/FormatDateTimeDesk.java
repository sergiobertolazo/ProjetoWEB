 
package  util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

 
public class FormatDateTimeDesk {

  // Formata Datas de acordo com o páis (adiciona AM e PM ao US)
  private FormatDateTimeDesk() {
  }

  public static DateTime parseToDateTime(String value, String country) {
    try {
      Date date = null;
      if (country.equals("US")) {
        date = new SimpleDateFormat("MM/dd/yyyy hh:mm aa").parse(value);
      }
      else {
        date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(value);
      }
      String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(date);
      return DateTime.parse(format);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String parseToStringDateTime(String value, String country) {
    try {
      String time = null;
      Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value);
      if (country.equals("US")) {
        time = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa").format(date);
      }
      else {
        time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
      }
      return time;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static LocalDate parseToLocalDate(String value, String country) {
    try {
      Date date = null;
      if (country.equals("US")) {
        date = new SimpleDateFormat("MM/dd/yyyy").parse(value);
      }
      else {
        date = new SimpleDateFormat("dd/MM/yyyy").parse(value);
      }
      String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
      return LocalDate.parse(format);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public static String parseToStringLocalDate(String value, String country) {
    try {
      String time = null;
      Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
      if (country.equals("US")) {
        time = new SimpleDateFormat("MM/dd/yyyy").format(date);
      }
      else {
        time = new SimpleDateFormat("dd/MM/yyyy").format(date);
      }
      return time;
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}