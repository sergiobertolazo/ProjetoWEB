 
package  util;

import java.sql.ResultSet;

import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  jdbc.SqlStatementWrapper;

 
public class GerarCodigo {

  private final String tabela;

  // Gerar o codigo das tabelas automaticamente
  public GerarCodigo(String tabela) {
    this.tabela = tabela;
  }

  public String getCodigo() {
    String res = query().getCodigo();
    if (res != null) {
      return res;
    } else {
      return tabela.substring(0, 1) + "1000";
    }
  }

  private GerarCodigoBuilder query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select concat('" + tabela.substring(0, 1) + "',max(ID)+1000) CODIGO")
        .with("from FLIGHT." + tabela)

        .load(new GerarCodigoLoader())

        .andGet();
  }

  private class GerarCodigoLoader implements ResultSetJdbcLoader<GerarCodigoBuilder> {
    @Override
    public GerarCodigoBuilder get(ResultSet resultSet) {
      ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, "");
      return new GerarCodigoBuilder(rs).createInstance();
    }
  }

  private class GerarCodigoBuilder implements Builder<GerarCodigoBuilder> {

    private final ResultSetJdbcWrapper rs;
    private String codigo;

    public GerarCodigoBuilder(ResultSetJdbcWrapper rs) {
      this.rs = rs;
    }

    @Override
    public GerarCodigoBuilder createInstance() {
      codigo = rs.getString("CODIGO");
      return this;
    }

    public String getCodigo() {
      return codigo;
    }

  }

}