
package  jdbc;

import java.sql.Blob;
import java.sql.ResultSet;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;


public interface ResultSetJdbc {

  ResultSet getResultSet();

  String getString(String columnLabel);

  int getInt(String columnLabel);

  double getDouble(String columnLabel);

  boolean getBoolean(String columnLabel);

  DateTime getDateTime(String columnLabel);

  LocalDate getLocalDate(String columnLabel);

  long getLong(String columnLabel);

  Blob getBlob(String columnLabel);

}