 
package  util;

import java.io.InputStream;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

 
public interface RequestParam {

  Integer intParam(String param);

  Long longParam(String param);

  Double doubleParam(String param);

  DateTime dateTimeParam(String param);

  LocalDate localDateParam(String param);

  <E extends Enum<E>> E enumParam(Class<E> enumClass, String param);

  String stringParam(String param);

  Boolean booleanParam(String param);

  InputStream inputStreamParam(String param);

  String getCountry();

}