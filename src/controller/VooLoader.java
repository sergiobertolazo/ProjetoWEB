 
package  controller;

import java.sql.ResultSet;

import org.joda.time.DateTime;

import  jdbc.ResultSetJdbc;
import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  model.Aeronave;
import  model.Status;
import  model.Voo;
import  model.VooModel;

 
public class VooLoader implements ResultSetJdbcLoader<Voo> {

  private final String alias;

  public VooLoader() {
    this.alias = "VOO";
  }

  @Override
  public Voo get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new VooBuilder(rs).createInstance();
  }

  private class VooBuilder implements Voo.Builder {

    private final ResultSetJdbc rs;

    public VooBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Voo createInstance() {
      VooModel impl = new VooModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public Aeronave getAeronave() {
      ResultSet resultSet = rs.getResultSet();
      return new AeronaveLoader().get(resultSet);
    }

    @Override
    public String getOrigem() {
      return rs.getString("ORIGEM");
    }

    @Override
    public String getDestino() {
      return rs.getString("DESTINO");
    }

    @Override
    public String getEscala() {
      return rs.getString("ESCALA");
    }

    @Override
    public DateTime getDataDePartida() {
      return rs.getDateTime("DATA_PARTIDA");
    }

    @Override
    public DateTime getDataDeChegada() {
      return rs.getDateTime("DATA_CHEGADA");
    }

    @Override
    public String getObservacao() {
      return rs.getString("OBSERVACAO");
    }

    @Override
    public Status getStatus() {
      int val = rs.getInt("STATUS");
      Status[] status = Status.values();
      return status[val];
    }

    @Override
    public int getAssentoLivre() {
      return rs.getInt("ASSENTO_LIVRE");
    }

    @Override
    public double getPreco() {
      return rs.getDouble("PRECO");
    }

  }

}