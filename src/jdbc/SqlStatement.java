 
package  jdbc;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

 
public interface SqlStatement {

  SqlStatement prepare();

  SqlStatement with(String syntax);

  SqlStatement with(String syntax, Object object);

  SqlStatement load(ResultSetJdbcLoader<?> loader);

  <T> List<T> andList();

  <T> T andGet();

  boolean andExecute() throws SQLException;

  SqlStatement with(String syntax, Object object, File image);

}