 
package model;

import java.sql.SQLException;

import dao.ReembolsoDAO;
import jdbc.Database;
import jdbc.FactoryJdbc;
import util.CPF;

 
public class ReembolsoModel implements Reembolso {

  private int id;
  private Passagem passagem;
  private String titular;
  private CPF cpf;
  private int banco;
  private int agencia;
  private int conta;
  private double valor;
  private ReembolsoDAO dao;

  public ReembolsoModel(Builder builder) {
    this.passagem = builder.getPassagem();
    this.titular = builder.getTitular();
    this.cpf = builder.getCpf();
    this.banco = builder.getBanco();
    this.agencia = builder.getAgencia();
    this.conta = builder.getConta();
    this.valor = builder.getValor();
  }

  public ReembolsoModel() {
    Database database = FactoryJdbc.getInstance().getDatabase();
    if(database.equals(Database.MYSQL))
      dao = new ReembolsoDAO();
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
  public Passagem getPassagem() {
    return passagem;
  }

  @Override
  public String getTitular() {
    return titular;
  }

  @Override
  public CPF getCpf() {
    return cpf;
  }

  @Override
  public int getBanco() {
    return banco;
  }

  @Override
  public int getAgencia() {
    return agencia;
  }

  @Override
  public int getConta() {
    return conta;
  }

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public boolean criar(Reembolso reembolso) throws SQLException {
    return dao.criar(reembolso);
  }

}