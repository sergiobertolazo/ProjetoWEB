 
package  jdbc;

import java.sql.Connection;
 
public interface Conexao {

  String getDriverClass();

  String getUrl();

  String getUser();

  String getPassword();

  Connection getConexao();

}