 
package jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.FlightCore;
import util.Param;

 
public class SqlStatementWrapper implements SqlStatement {

  private static final FlightCore core = FlightCore.getInstance();

  // Esta classe esta por trás de tudo q acontece na fluent Interface dos DAOs
  // Resumindo, Cria-se um statement de acordo com os parametros e executa o sql
  // montado
  private int value;
  private Connection connection;
  private PreparedStatement stm;
  private String syntax;
  private final List<Param<?>> params;
  private ResultSetJdbcLoader<?> loader;
  private File image;

  public SqlStatementWrapper() {
    this.syntax = "";
    this.params = new ArrayList<Param<?>>();
  }

  @Override
  public SqlStatement prepare() {
    Conexao impl = new ConexaoImpl();
    connection = impl.getConexao();
    return this;
  }

  @Override
  public SqlStatement with(String syntax) {
    this.syntax += syntax + " ";
    return this;
  }

  @Override
  public SqlStatement with(String syntax, Object object) {
    if (object != null) {
      Param<?> param = Param.parseValue(object);
      params.add(param);
      with(syntax);
    }
    return this;
  }

  @Override
  public SqlStatement with(String syntax, Object object, File image) {
    if (object != null) {
      Param<?> param = Param.parseValue(object);
      params.add(param);
      with(syntax);
      this.image = image;
    }
    return this;
  }

  @Override
  public SqlStatement load(ResultSetJdbcLoader<?> loader) {
    this.loader = loader;
    return this;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> List<T> andList() {
    List<T> list = new ArrayList<T>();
    try {
      stm = connection.prepareStatement(this.syntax);
    
      SqlStatementExecute.setStmt(stm, params, value, image);
      core.logInfo(stm.toString());
      stm.execute();
      ResultSet resultSet = stm.getResultSet();

      while (resultSet.next()) {
        T t = (T) loader.get(resultSet);
        list.add(t);
      }

      stm.close();
      resultSet.close();
      connection.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T andGet() {
    T t = null;
    ResultSet resultSet = null;
    try {
      stm = connection.prepareStatement(this.syntax);

      SqlStatementExecute.setStmt(stm, params, value, image);
      core.logInfo(stm.toString());
      stm.execute();
      resultSet = stm.getResultSet();

      while (resultSet.next()) {
        t = (T) loader.get(resultSet);
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        stm.close();
        resultSet.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return t;
  }

  @Override
  public boolean andExecute() throws SQLException {
    boolean res = false;
    try {
      stm = connection.prepareStatement(this.syntax);
      SqlStatementExecute.setStmt(stm, params, value, image);
      core.logInfo(stm.toString());
      stm.executeUpdate();

      res = true;
      return res;
    } catch (SQLException e) {
      core.logError(e.toString(), e);
      throw new SQLException(e);
    } finally {
      stm.close();
      connection.close();
    }
  }

}