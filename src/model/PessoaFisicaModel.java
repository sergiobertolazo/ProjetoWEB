
package model;

import java.sql.SQLException;

import org.joda.time.LocalDate;

import dao.PessoaFisicaDAO;
import jdbc.Database;
import jdbc.FactoryJdbc;
import util.CPF;

 
public class PessoaFisicaModel implements PessoaFisica {

  private int id;
  private String nome;
  private String sobrenome;
  private LocalDate dataDeNascimento;
  private CPF cpf;
  private String rg;
  private String endereco;
  private long telResidencial;
  private long telCelular;
  private String email;
  private PessoaFisicaDAO dao;

  public PessoaFisicaModel(Builder builder) {
    this.nome = builder.getNome();
    this.sobrenome = builder.getSobrenome();
    this.dataDeNascimento = builder.getDataNascimento();
    this.cpf = builder.getCpf();
    this.rg = builder.getRg();
    this.endereco = builder.getEndereco();
    this.telResidencial = builder.getTelResidencial();
    this.telCelular = builder.getTelCelular();
    this.email = builder.getEmail();
  }

  public PessoaFisicaModel() {
    Database database = FactoryJdbc.getInstance().getDatabase();
    if(database.equals(Database.MYSQL))
      dao = new PessoaFisicaDAO();
    if(database.equals(Database.ORACLE))
      dao = null;
    if(database.equals(Database.SQL_SERVER))
      dao = null;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public String getSobrenome() {
    return sobrenome;
  }

  @Override
  public LocalDate getDataNascimento() {
    return dataDeNascimento;
  }

  @Override
  public CPF getCpf() {
    return cpf;
  }

  @Override
  public String getRg() {
    return rg;
  }

  @Override
  public String getEndereco() {
    return endereco;
  }

  @Override
  public long getTelResidencial() {
    return telResidencial;
  }

  @Override
  public long getTelCelular() {
    return telCelular;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public boolean criar(PessoaFisica pojo) throws SQLException {
    return dao.criar(pojo);
  }

  @Override
  public void atualizar(PessoaFisica pojo) throws SQLException {
    dao.atualizar(pojo);
  }

  @Override
  public PessoaFisica consultarPorCPF(CPF cpf) {
    return dao.consultarPorCpf(cpf);
  }

}