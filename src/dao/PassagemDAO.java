 
package dao;

import java.sql.SQLException;
import java.util.List;

import controller.PassagemLoader;
import jdbc.SqlStatement;
import jdbc.SqlStatementWrapper;
import model.Passagem;
import model.Voo;

 
public class PassagemDAO implements Passagem.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.PASSAGEM as PASSAGEM")

        .with("join FLIGHT.PESSOAFISICA as PESSOAFISICA")
        .with("on PASSAGEM.PESSOAFISICA_ID = PESSOAFISICA.ID")

        .with("left join FLIGHT.VOO as VOO")
        .with("on PASSAGEM.VOO_ID = VOO.ID")

        .with("left join FLIGHT.AERONAVE as AERONAVE")
        .with("on VOO.AERONAVE_ID = AERONAVE.ID")

        .load(new PassagemLoader());
  }

  @Override
  public boolean vender(Passagem passagem) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.PASSAGEM")
        .with("(VOO_ID, PESSOAFISICA_ID, COD_BILHETE)")
        .with("values(")
        .with("?,", passagem.getVoo().getId())
        .with("?,", passagem.getPessoaFisica().getId())
        .with("?)", passagem.getCodigoBilhete())

        .andExecute();
    return executed;
  }

  @Override
  public Passagem consultarPorCodigoBilhete(String bilhete) {
    return query()

        .with("where PASSAGEM.COD_BILHETE = ?", bilhete)

        .andGet();
  }

  @Override
  public boolean transferir(Passagem passagem) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("VOO_ID = ?", passagem.getVoo().getId())

        .with("where ID = ?", passagem.getId())

        .andExecute();
    return executed;
  }

  @Override
  public List<Passagem> consultarPorVoo(Voo voo) {
    return query()

        .with("where PASSAGEM.VOO_ID = ?", voo.getId())
        .with("and VOO.ASSENTO_LIVRE > 0")

        .andList();
  }

  @Override
  public boolean efetuarCheckin(Passagem pojo, String assento) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("ASSENTO = ?", assento)

        .with("where ID = ?", pojo.getId())

        .andExecute();
    return executed;
  }

  @Override
  public boolean cancelarPorVoo(Voo pojo) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("VOO_ID = NULL")
        .with("where VOO_ID = ?", pojo.getId())

        .andExecute();
    return executed;

  }

  @Override
  public boolean efetuarCancelamento(Passagem pojo) throws SQLException {
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.PASSAGEM as PASSAGEM set")
        .with("VOO_ID = null")
        .with("where ID = ?", pojo.getId())

        .andExecute();
    return executed;
  }

  @Override
  public Passagem consultarPorId(int id) {
    return query()

        .with("where PASSAGEM.ID = ?", id)

        .andGet();

  }

}