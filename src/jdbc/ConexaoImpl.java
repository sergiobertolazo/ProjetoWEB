
package  jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import  core.FlightCore;


public class ConexaoImpl implements Conexao {

  private final FlightCore core = FlightCore.getInstance();
  private String driverClass;
  private String url;
  private String user;
  private String password;

  @Override
  public Connection getConexao() {
    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/jdbc.properties")));

      Properties properties = new Properties();
      properties.load(reader);

      driverClass = properties.getProperty("driver");
      url = properties.getProperty("url");
      user = properties.getProperty("user");
      password = properties.getProperty("password");

      Class.forName(driverClass);

      Connection connection = DriverManager.getConnection(url, user, password);
      return connection;
    } catch (Exception e) {
      core.logError(e.getMessage(), e);
      return null;
    }
  }

  @Override
  public String getDriverClass() {
    return driverClass;
  }

  @Override
  public String getUrl() {
    return url;
  }

  @Override
  public String getUser() {
    return user;
  }

  @Override
  public String getPassword() {
    return password;
  }

}