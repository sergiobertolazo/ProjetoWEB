 
package  util;

import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;

 
// Verifica Strings vindo da View
public class VerifierString {

  public static boolean containsSpace(String word) {
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(word);
    return matcher.find();
  }

  public static boolean isBirthDay(String word, String country) {
    boolean birthDay = false;
    LocalDate date = FormatDateTimeDesk.parseToLocalDate(word, country);
    boolean leapYear = new GregorianCalendar().isLeapYear(date.getYear());
    LocalDate now = new LocalDate();

    // US MM/DD/YYYY
    if (country.equals("US")) {
      // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
      if (!word.startsWith("02/30") && !word.startsWith("02/31")
          && date.isBefore(now) || date.isEqual(now)) {
        // Se for bissexto deve considerar 02/29
        if (word.startsWith("02/29") && leapYear || !word.startsWith("02/29")) {
          Pattern pattern = Pattern
              .compile("(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }
      }

      // BR DD/MM/YYYY
    } else {
      if (!word.startsWith("30/02") && !word.startsWith("31/02")
          && date.isBefore(now) || date.isEqual(now)) {
        // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
        if (word.startsWith("29/02") && leapYear || !word.startsWith("29/02")) {
          // Se for bissexto deve considerar 29/02
          Pattern pattern = Pattern
              .compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }
      }
    }

    return birthDay;
  }

  public static boolean isDateValid(String word, ResourceBundle bundle) {
    boolean birthDay = false;
    String country = bundle.getString("country");
    DateTime date = FormatDateTimeDesk.parseToDateTime(word, country);
    boolean leapYear = new GregorianCalendar().isLeapYear(date.getYear());

    // US MM/DD/YYYY
    if (country.equals("US")) {
      // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
      if (!word.startsWith("02/30") && !word.startsWith("02/31")) {
        // Se for bissexto deve considerar 02/29
        if (word.startsWith("02/29") && leapYear || !word.startsWith("02/29")) {
          Pattern pattern = Pattern
              .compile("(0[1-9]|1[012])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d) (0[0-9]|1[0-9]|2[0-4]):([0-5][0-9])");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }

      }

      // BR DD/MM/YYYY
    } else {
      if (!word.startsWith("30/02") && !word.startsWith("31/02")) {
        // Não pode conter 30 e 31 em fevereiro e nem ser maior que a data atual
        if (word.startsWith("29/02") && leapYear || !word.startsWith("29/02")) {
          // Se for bissexto deve considerar 29/02
          Pattern pattern = Pattern
              .compile("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d) (0[0-9]|1[0-9]|2[0-4]):([0-5][0-9])");
          Matcher matcher = pattern.matcher(word);
          birthDay = matcher.find();
        }
      }
    }

    return birthDay;
  }
  public static boolean isValidate(String word) {
    try {
      boolean validate = false;
      LocalDate now = new LocalDate();
      String year = word.substring(3, 7);
      String month = word.substring(0, 2);

      LocalDate date = new LocalDate()
          .withYear(Integer.parseInt(year))
          .withMonthOfYear(Integer.parseInt(month));

      if (date.isAfter(now) || date.isEqual(now)) {
        Pattern pattern = Pattern.compile("(0[1-9]|1[012])/((19|20)\\d\\d)");
        Matcher matcher = pattern.matcher(word);
        validate = matcher.find();
      }

      return validate;
    } catch (IllegalFieldValueException e) {
      return false;
    }
  }

}