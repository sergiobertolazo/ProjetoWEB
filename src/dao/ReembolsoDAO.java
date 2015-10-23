 
package  dao;

import java.sql.SQLException;

import  jdbc.SqlStatementWrapper;
import  model.Passagem;
import  model.Reembolso;

 
public class ReembolsoDAO implements Reembolso.Jdbc {

  @Override
  public boolean criar(Reembolso reembolso) throws SQLException {
    Passagem passagem = reembolso.getPassagem();
    boolean executed = new SqlStatementWrapper()
        .prepare()

        .with("insert into FLIGHT.REEMBOLSO")
        .with("(ID, PASSAGEM_ID, TITULAR, CPF, BANCO, AGENCIA, CONTA, VALOR)")

        .with("values (")
        .with("?,", reembolso.getId())
        .with("?,", passagem.getId())
        .with("?,", reembolso.getTitular())
        .with("?,", reembolso.getCpf().getDigito())
        .with("?,", reembolso.getBanco())
        .with("?,", reembolso.getAgencia())
        .with("?,", reembolso.getConta())
        .with("?)", reembolso.getValor())

        .andExecute();
    return executed;
  }

}