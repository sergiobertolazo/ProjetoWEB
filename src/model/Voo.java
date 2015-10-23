 
package model;

import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;

import util.RequestParam;

 
public interface Voo {

  interface Builder extends util.Builder<Voo> {

    String getCodigo();

    Aeronave getAeronave();

    String getOrigem();

    String getDestino();

    String getEscala();

    DateTime getDataDePartida();

    DateTime getDataDeChegada();

    String getObservacao();

    Status getStatus();

    int getAssentoLivre();

    double getPreco();

  }

  interface Jdbc {

    List<Voo> consultaPainel();

    boolean criar(Voo voo) throws SQLException;

    List<Voo> consultar(RequestParam request);

    Voo consultarPorId(int id);

    boolean atualizar(Voo voo) throws SQLException;

    void deletar(int id) throws SQLException;

    void deletarPorAeronaveId(int id) throws SQLException;

    void controlarStatus(int id, Status status) throws SQLException;

    void incrementarAssento(int id) throws SQLException;

    void decrementarAssento(int id) throws SQLException;

    List<Voo> consultarPorAeronaveId(int id);
  }

  int getId();

  String getCodigo();

  Aeronave getAeronave();

  String getOrigem();

  String getDestino();

  String getEscala();

  DateTime getDataDePartida();

  DateTime getDataDeChegada();

  String getObservacao();

  Status getStatus();

  int getAssentoLivre();

  double getPreco();

  List<Voo> consultaPainel();

  boolean criar(Voo pojo) throws SQLException;

  List<Voo> consultar(RequestParam request);

  Voo consultarPorId(int id);

  boolean atualizar(Voo pojo) throws SQLException;

  void deletar(int id) throws SQLException;

  void deletaPorAeronaveId(int aeronaveId) throws SQLException;

  void controlarStatus(int id, Status fromString) throws SQLException;

  void incrementarAssento(int vooId) throws SQLException;

  void decrementarAssento(int vooId) throws SQLException;

  List<Voo> consultarPorAeronaveId(int id);

}