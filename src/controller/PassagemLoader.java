 
package  controller;

import java.sql.ResultSet;

import  jdbc.ResultSetJdbc;
import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  model.Passagem;
import  model.PassagemModel;
import  model.PessoaFisica;
import  model.Voo;

 
public class PassagemLoader implements ResultSetJdbcLoader<Passagem> {

  private final String alias;

  public PassagemLoader() {
    this.alias = "PASSAGEM";
  }

  @Override
  public Passagem get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new PassagemBuilder(rs).createInstance();
  }

  private class PassagemBuilder implements Passagem.Builder {

    private final ResultSetJdbc rs;

    public PassagemBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Passagem createInstance() {
      PassagemModel impl = new PassagemModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public Voo getVoo() {
      ResultSet resultSet = rs.getResultSet();
      return new VooLoader().get(resultSet);
    }

    @Override
    public PessoaFisica getPessoaFisica() {
      ResultSet resultSet = rs.getResultSet();
      return new PessoaFisicaLoader().get(resultSet);
    }

    @Override
    public String getCodigoBilhete() {
      return rs.getString("COD_BILHETE");
    }

    @Override
    public String getAssento() {
      return rs.getString("ASSENTO");
    }
  }

}
