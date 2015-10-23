 
package model;

import java.sql.SQLException;

import org.joda.time.LocalDate;

import util.CPF;

 
public interface PessoaFisica {

  interface Builder extends util.Builder<PessoaFisica> {

    String getNome();

    String getSobrenome();

    LocalDate getDataNascimento();

    CPF getCpf();

    String getRg();

    String getEndereco();

    long getTelResidencial();

    long getTelCelular();

    String getEmail();

  }

  interface Jdbc {

    boolean criar(PessoaFisica pessoaFisica) throws SQLException;

    PessoaFisica consultarPorCpf(CPF cpf);

    boolean atualizar(PessoaFisica pessoaFisica) throws SQLException;

  }

  int getId();

  String getNome();

  String getSobrenome();

  LocalDate getDataNascimento();

  CPF getCpf();

  String getRg();

  String getEndereco();

  long getTelResidencial();

  long getTelCelular();

  String getEmail();

  boolean criar(PessoaFisica pojo) throws SQLException;

  PessoaFisica consultarPorCPF(CPF cpf);

  void atualizar(PessoaFisica pojo) throws SQLException;

}