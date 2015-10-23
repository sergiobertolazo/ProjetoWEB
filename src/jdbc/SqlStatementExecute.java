 
package  jdbc;

import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import  util.Param;

 
class SqlStatementExecute {

  // Esta classe popula um statement de acordo com o tipo passado junto ao sql
  // Utilizado para a Fluent Interface
  static PreparedStatement setStmt(PreparedStatement stm, List<Param<?>> params, int value, File image) {

    for (int i = 0; i < params.size(); i++) {
      Object object = params.get(i).getValue();

      try {
        if (object instanceof Integer) {
          stm.setInt(++value, ((Integer) object).intValue());
        }

        if (object instanceof Long) {
          stm.setLong(++value, ((Long) object).longValue());
        }

        if (object instanceof Double) {
          stm.setDouble(++value, ((Double) object).doubleValue());
        }

        if (object instanceof String) {
          stm.setString(++value, ((String) object).toString());
        }

        if (object instanceof Boolean) {
          stm.setBoolean(++value, ((Boolean) object).booleanValue());
        }

        if (object instanceof LocalDate) {
          Date date = ((LocalDate) object).toDate();
          java.sql.Date localDate = new java.sql.Date(date.getTime());
          stm.setDate(++value, localDate);
        }

        if (object instanceof DateTime) {
          Date date = ((DateTime) object).toDate();
          Timestamp timestamp = new Timestamp(date.getTime());
          stm.setTimestamp(++value, timestamp);
        }

        if (object instanceof InputStream) {
          stm.setBinaryStream(++value, (InputStream) object, (int) (image.length()));
        }

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }
    return stm;

  }

}