 
package model;

import java.sql.SQLException;

import util.CPF;
 
public interface Reembolso {

  interface Builder extends util.Builder<Reembolso> {

    Passagem getPassagem();

    String getTitular();

    CPF getCpf();

    int getBanco();

    int getAgencia();

    int getConta();

    double getValor();
  }

  interface Jdbc {

    boolean criar(Reembolso reembolso) throws SQLException;

  }

  int getId();

  Passagem getPassagem();

  String getTitular();

  CPF getCpf();

  int getBanco();

  int getAgencia();

  int getConta();

  double getValor();

  boolean criar(Reembolso reembolso) throws SQLException;

}