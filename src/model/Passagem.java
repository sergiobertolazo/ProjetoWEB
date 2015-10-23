 
package model;

import java.sql.SQLException;
import java.util.List;

 
public interface Passagem {

  interface Builder extends util.Builder<Passagem> {

    Voo getVoo();

    PessoaFisica getPessoaFisica();

    String getCodigoBilhete();

    String getAssento();
  }

  interface Jdbc {

    boolean vender(Passagem passagem) throws SQLException;

    Passagem consultarPorCodigoBilhete(String bilhete);

    boolean transferir(Passagem passagem) throws SQLException;

    List<Passagem> consultarPorVoo(Voo voo);

    boolean efetuarCheckin(Passagem pojo, String assento) throws SQLException;

    boolean efetuarCancelamento(Passagem pojo) throws SQLException;

    boolean cancelarPorVoo(Voo pojo) throws SQLException;

    Passagem consultarPorId(int id);

  }

  int getId();

  Voo getVoo();

  PessoaFisica getPessoaFisica();

  String getCodigoBilhete();

  String getAssento();

  boolean vender(Passagem pojo) throws SQLException;

  Passagem consultarPorCodigoBilhete(String bilhete);

  boolean transferir(Passagem passagem) throws SQLException;

  List<Passagem> consultarPorVoo(Voo voo);

  boolean efetuarCheckin(Passagem pojo, String assento) throws SQLException;

  boolean efetuarCancelamento(Passagem pojo) throws SQLException;

  boolean cancelarPorVoo(Voo voo) throws SQLException;

  double getPreco(Passagem passagem);

  Passagem consultarPorId(int id);

}