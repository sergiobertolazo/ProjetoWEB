
package dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import controller.AeronaveLoader;
import jdbc.SqlStatement;
import jdbc.SqlStatementWrapper;
import model.Aeronave;
import util.RequestParam;

public class AeronaveDAO implements Aeronave.Jdbc {

  private SqlStatement query() {
    return new SqlStatementWrapper()
        .prepare()

        .with("select *")
        .with("from FLIGHT.AERONAVE as AERONAVE")

        .load(new AeronaveLoader());
  }

  @Override
  public void criar(Aeronave aeronave, File image) throws SQLException {
    new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.AERONAVE")
        .with("(NOME, CODIGO, QTD_ASSENTO, IMAGEM, MAPA)")
        .with("values (")
        .with("?,", aeronave.getNome())
        .with("?,", aeronave.getCodigo())
        .with("?,", aeronave.getQtdDeAssento())
        .with("?,", aeronave.getCode(), image)
        .with("?)", aeronave.isMapa())

        .andExecute();
  }

  @Override
  public List<Aeronave> consultar(RequestParam request) {
    String nome = request.stringParam("nome");
    String codigo = request.stringParam("codigo");

    return query()

        .with("where 1 = 1")
        .with("and AERONAVE.NOME like concat ('%',?,'%')", nome)
        .with("and AERONAVE.CODIGO like concat ('%',?,'%')", codigo)
        .with("order by AERONAVE.CODIGO asc")

        .andList();
  }

  @Override
  public Aeronave consultarPorId(int id) {
    return query()

        .with("where ID = ?", id)

        .andGet();
  }

  @Override
  public void atualizar(Aeronave aeronave) throws SQLException {
    String nome = aeronave.getNome().isEmpty() ? null : aeronave.getNome();
    new SqlStatementWrapper()
        .prepare()

        .with("update FLIGHT.AERONAVE AS AERONAVE set")
        .with("AERONAVE.NOME = ?", nome)
        .with("where AERONAVE.ID = ?", aeronave.getId())

        .andExecute();
  }

  @Override
  public boolean deletar(int id) throws SQLException {
    return new SqlStatementWrapper()
        .prepare()

        .with("delete from FLIGHT.AERONAVE")
        .with("where ID = ?", id)

        .andExecute();
  }

}