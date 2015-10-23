package model;

import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import dao.PassagemDAO;
import jdbc.Database;
import jdbc.FactoryJdbc;

public class PassagemModel implements Passagem {

  private int id;
  private Voo voo;
  private PessoaFisica pf;
  private String codigoBilhete;
  private String assento;
  private PassagemDAO dao;

  public PassagemModel(Builder builder) {
    this.voo = builder.getVoo();
    this.pf = builder.getPessoaFisica();
    this.codigoBilhete = builder.getCodigoBilhete();
    this.assento = builder.getAssento();
  }

  public PassagemModel() {
    Database database = FactoryJdbc.getInstance().getDatabase();
    if(database.equals(Database.MYSQL))
      dao = new PassagemDAO();
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
  public Voo getVoo() {
    return voo;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return pf;
  }

  @Override
  public String getCodigoBilhete() {
    return codigoBilhete;
  }

  @Override
  public String getAssento() {
    return assento;
  }

  @Override
  public double getPreco(Passagem passagem) {
    Voo voo = passagem.getVoo();

    DateTime partida = voo.getDataDePartida();
    DateTime agora = new DateTime().toDateTime();
    int horas = Hours.hoursBetween(agora, partida).getHours();

    if (partida.isAfterNow()) {

      if (horas >= 24) {
        return voo.getPreco();
      } else if (horas >= 12) {
        return (voo.getPreco() * 0.5);
      } else if (horas >= 6) {
        return (voo.getPreco() * 0.25);
      } else {
        return 0.0;
      }

    } else {
      return -1;
    }
  }

  @Override
  public boolean transferir(Passagem pojo) throws SQLException {
    return dao.transferir(pojo);
  }

  @Override
  public boolean vender(Passagem pojo) throws SQLException {
    return dao.vender(pojo);
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    return dao.consultarPorCodigoBilhete(bilhete);
  }

  @Override
  public List<Passagem> consultarPorVoo(Voo voo) {
    return dao.consultarPorVoo(voo);
  }

  @Override
  public boolean efetuarCheckin(Passagem pojo, String assento) throws SQLException {
    return dao.efetuarCheckin(pojo, assento);
  }

  @Override
  public boolean cancelarPorVoo(Voo pojo) throws SQLException {
    return dao.cancelarPorVoo(pojo);
  }

  @Override
  public boolean efetuarCancelamento(Passagem pojo) throws SQLException {
    return dao.efetuarCancelamento(pojo);
  }

  @Override
  public Passagem consultarPorId(int id) {
    return dao.consultarPorId(id);
  }

}