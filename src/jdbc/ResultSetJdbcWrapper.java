 
package jdbc;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

 
public class ResultSetJdbcWrapper implements ResultSetJdbc {

  private final ResultSet rs;
  private final String alias;

  // Esta classe, como o nome ja diz, é um Wrapper de ResultSets
  public ResultSetJdbcWrapper(ResultSet rs, String alias) {
    this.rs = rs;
    this.alias = alias;
  }

  @Override
  public ResultSet getResultSet() {
    return rs;
  }

  @Override
  public String getString(String columnLabel) {
    try {
      return rs.getString(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int getInt(String columnLabel) {
    try {
      return rs.getInt(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public double getDouble(String columnLabel) {
    try {
      return rs.getDouble(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getBoolean(String columnLabel) {
    try {
      return rs.getBoolean(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public DateTime getDateTime(String columnLabel) {
    try {
      Timestamp timestamp = rs.getTimestamp(alias + "." + columnLabel);
      return new DateTime(timestamp);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public LocalDate getLocalDate(String columnLabel) {
    try {
      Date date = rs.getDate(alias + "." + columnLabel);
      return new LocalDate(date);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public long getLong(String columnLabel) {
    try {
      return rs.getLong(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Blob getBlob(String columnLabel) {
    try {
      return rs.getBlob(alias + "." + columnLabel);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}