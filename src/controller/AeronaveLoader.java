
package  controller;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import  core.FlightCore;
import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  model.Aeronave;
import  model.AeronaveModel;


public class AeronaveLoader implements ResultSetJdbcLoader<Aeronave> {

  private final FlightCore core = FlightCore.getInstance();
  private final String alias;

  public AeronaveLoader() {
    this.alias = "AERONAVE";
  }

  @Override
  public Aeronave get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new AeronaveBuilder(rs).createInstance();
  }

  private class AeronaveBuilder implements Aeronave.Builder {

    private final ResultSetJdbcWrapper rs;

    public AeronaveBuilder(ResultSetJdbcWrapper rs) {
      this.rs = rs;
    }

    @Override
    public Aeronave createInstance() {
      AeronaveModel impl = new AeronaveModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public String getNome() {
      return rs.getString("NOME");
    }

    @Override
    public int getQtdDeAssento() {
      return rs.getInt("QTD_ASSENTO");
    }

    @Override
    public boolean isMapa() {
      return rs.getBoolean("MAPA");
    }

    @Override
    public InputStream getCode() {
      try {
        Blob blob = rs.getBlob("IMAGEM");
        return blob != null ? blob.getBinaryStream() : null;
      } catch (SQLException e) {
        try {
          throw new SQLException(e);
        } catch (SQLException e1) {
          core.logError("Error Blob", e);
        }
      }
      return null;
    }

  }

}