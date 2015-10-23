 
package  jdbc;

import java.sql.ResultSet;

 
public interface ResultSetJdbcLoader<T> {

  // Esta interface deve ser implementada por todos os Loaders
  // Nossos result sets
  T get(ResultSet resultSet);

}