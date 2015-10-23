
package dao;

import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;

import controller.VooLoader;
import jdbc.SqlStatement;
import jdbc.SqlStatementWrapper;
import model.Aeronave;
import model.Status;
import model.Voo;
import util.RequestParam;


public class VooDAO implements Voo.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.VOO as VOO")

        .with("join FLIGHT.AERONAVE as AERONAVE")
        .with("on AERONAVE.ID = VOO.AERONAVE_ID")

        .load(new VooLoader());
  }

  @Override
  public boolean criar(Voo voo) throws SQLException {
    boolean executed = false;
    if (voo != null) {
      Aeronave aeronave = voo.getAeronave();
      executed = new SqlStatementWrapper()
          .prepare()

          .with("insert into FLIGHT.VOO")
          .with("(AERONAVE_ID, CODIGO, ORIGEM, DESTINO, ESCALA,")
          .with("DATA_PARTIDA, DATA_CHEGADA, STATUS, ASSENTO_LIVRE, PRECO)")

          .with("values (")
          .with("?,", aeronave.getId())
          .with("?,", voo.getCodigo())
          .with("?,", voo.getOrigem())
          .with("?,", voo.getDestino())
          .with("?,", voo.getEscala())
          .with("?,", voo.getDataDePartida())
          .with("?,", voo.getDataDeChegada())
          .with("?,", voo.getStatus().ordinal())
          .with("?,", voo.getAssentoLivre())
          .with("?)", voo.getPreco())

          .andExecute();
    }
    return executed;
  }

  @Override
  public List<Voo> consultar(RequestParam request) {
    Status status = request.enumParam(Status.class, "status");
    Integer ordinal = status != null ? status.ordinal() : null;

    DateTime partida = request.dateTimeParam("partida");
    DateTime chegada = request.dateTimeParam("chegada");
    String origem = request.stringParam("origem");
    String destino = request.stringParam("destino");
    Integer assento = request.intParam("assento");

    return query()

        .with("where 1 = 1")
        .with("and VOO.STATUS = ?", ordinal)
        .with("and VOO.DATA_PARTIDA >= ?", partida)
        .with("and VOO.DATA_CHEGADA <= ?", chegada)
        .with("and VOO.ORIGEM like concat ('%', ?, '%')", origem)
        .with("and VOO.DESTINO like concat ('%', ?, '%')", destino)
        .with("and VOO.ASSENTO_LIVRE > ?", assento)

        .with("order by VOO.DATA_PARTIDA asc")

        .andList();
  }

  @Override
  public List<Voo> consultaPainel() {
    return query()

        .with("order by VOO.STATUS asc,")
        .with("VOO.DATA_PARTIDA asc")
        .with("limit 0,15")

        .andList();
  }

  @Override
  public Voo consultarPorId(int id) {
    return query()

        .with("where VOO.ID = ?", id)

        .andGet();
  }

  @Override
  public List<Voo> consultarPorAeronaveId(int id) {
    return query()

        .with("where VOO.AERONAVE_ID = ?", id)

        .andList();
  }

  @Override
  public boolean atualizar(Voo voo) throws SQLException {
    boolean executed = false;
    if (voo != null) {
      executed = new SqlStatementWrapper()
          .prepare()

          .with("update FLIGHT.VOO set")
          .with("DATA_PARTIDA = ?,", voo.getDataDePartida())
          .with("DATA_CHEGADA = ?,", voo.getDataDeChegada())
          .with("OBSERVACAO = ?", voo.getObservacao())

          .with("where ID = ?", voo.getId())

          .andExecute();
    }
    return executed;
  }

  @Override
  public void deletar(int id) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.VOO")
        .with("where ID = ?", id)

        .andExecute();
  }

  @Override
  public void deletarPorAeronaveId(int id) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.VOO")
        .with("where AERONAVE_ID = ?", id)

        .andExecute();
  }

  @Override
  public void controlarStatus(int id, Status status) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.VOO set")
        .with("STATUS = ?", status.ordinal())

        .with("where ID = ?", id)

        .andExecute();
  }

  @Override
  public void incrementarAssento(int id) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.VOO set")
        .with("ASSENTO_LIVRE = (ASSENTO_LIVRE+1)")

        .with("where ID = ?", id)

        .andExecute();
  }

  @Override
  public void decrementarAssento(int id) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.VOO set")
        .with("ASSENTO_LIVRE = (ASSENTO_LIVRE-1)")

        .with("where ID = ?", id)

        .andExecute();
  }

}