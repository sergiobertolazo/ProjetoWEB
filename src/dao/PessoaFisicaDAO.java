
package  dao;

import java.sql.SQLException;

import  controller.PessoaFisicaLoader;
import  jdbc.SqlStatement;
import  jdbc.SqlStatementWrapper;
import  model.PessoaFisica;
import  util.CPF;


public class PessoaFisicaDAO implements PessoaFisica.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.PESSOAFISICA as PESSOAFISICA")

        .load(new PessoaFisicaLoader());
  }

  @Override
  public boolean criar(PessoaFisica pessoaFisica) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.PESSOAFISICA")
        .with("(NOME, SOBRENOME, DATA_NASCIMENTO, CPF, RG, ENDERECO, ")
        .with("TEL_RESIDENCIAL, TEL_CELULAR, EMAIL)")

        .with("values (")
        .with("?,", pessoaFisica.getNome())
        .with("?,", pessoaFisica.getSobrenome())
        .with("?,", pessoaFisica.getDataNascimento())
        .with("?,", pessoaFisica.getCpf().getDigito())
        .with("?,", pessoaFisica.getRg())
        .with("?,", pessoaFisica.getEndereco())
        .with("?,", pessoaFisica.getTelResidencial())
        .with("?,", pessoaFisica.getTelCelular())
        .with("?)", pessoaFisica.getEmail() == null ? "--" : pessoaFisica.getEmail())

        .andExecute();

    return executed;
  }

  @Override
  public PessoaFisica consultarPorCpf(CPF cpf) {
    return query()

        .with("where PESSOAFISICA.CPF = ?", cpf.getDigito())

        .andGet();
  }

  @Override
  public boolean atualizar(PessoaFisica pessoaFisica) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PESSOAFISICA AS PESSOAFISICA set")
        .with("NOME = ?,", pessoaFisica.getNome())
        .with("SOBRENOME = ?,", pessoaFisica.getSobrenome())
        .with("DATA_NASCIMENTO = ?,", pessoaFisica.getDataNascimento())
        .with("CPF = ?,", pessoaFisica.getCpf().getDigito())
        .with("RG = ?,", pessoaFisica.getRg())
        .with("ENDERECO = ?,", pessoaFisica.getEndereco())
        .with("TEL_RESIDENCIAL = ?,", pessoaFisica.getTelResidencial())
        .with("TEL_CELULAR = ?,", pessoaFisica.getTelCelular())
        .with("EMAIL = ?", pessoaFisica.getEmail())
        .with("where ID = ?", pessoaFisica.getId())

        .andExecute();

    return executed;
  }

}