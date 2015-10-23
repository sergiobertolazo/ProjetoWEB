
package  controller;

import java.sql.ResultSet;

import org.joda.time.LocalDate;

import  jdbc.ResultSetJdbc;
import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  util.CPF;


public class PessoaFisicaLoader implements ResultSetJdbcLoader<PessoaFisica> {

  private final String alias;

  public PessoaFisicaLoader() {
    this.alias = "PESSOAFISICA";
  }

  @Override
  public PessoaFisica get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new PessoaFisicaBuilder(rs).createInstance();
  }

  private class PessoaFisicaBuilder implements PessoaFisica.Builder {

    private final ResultSetJdbc rs;

    public PessoaFisicaBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public PessoaFisica createInstance() {
      PessoaFisicaModel impl = new PessoaFisicaModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getNome() {
      return rs.getString("NOME");
    }

    @Override
    public String getSobrenome() {
      return rs.getString("SOBRENOME");
    }

    @Override
    public LocalDate getDataNascimento() {
      return rs.getLocalDate("DATA_NASCIMENTO");
    }

    @Override
    public CPF getCpf() {
      long value = rs.getLong("CPF");
      return CPF.valueOf(value);
    }

    @Override
    public String getRg() {
      return rs.getString("RG");
    }

    @Override
    public String getEndereco() {
      return rs.getString("ENDERECO");
    }

    @Override
    public long getTelResidencial() {
      return rs.getInt("TEL_RESIDENCIAL");
    }

    @Override
    public long getTelCelular() {
      return rs.getInt("TEL_CELULAR");
    }

    @Override
    public String getEmail() {
      return rs.getString("EMAIL");
    }

  }

}