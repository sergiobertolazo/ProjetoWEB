 
package  controller;

import java.util.List;

import  dao.OperadoraDeCartaoDAO;
import  model.Cartao;

 
public class CartaoControl implements Cartao.jdbc {

  @Override
  public boolean debitar() {
    return true;
  }

  @Override
  public boolean creditar(Cartao cartao) {
    OperadoraDeCartaoDAO operadora = new OperadoraDeCartaoDAO();
    operadora.openFile();
    List<Cartao> cartoes = operadora.readFile();

    boolean valid = false;

    for (Cartao card : cartoes) {
      if (card.getTitular().equals(cartao.getTitular())
          && card.getNumero() == cartao.getNumero()
          && card.getDataDeValidade().isEqual(cartao.getDataDeValidade())
          && card.getBandeira() == cartao.getBandeira()) {
        valid = true;
      }
    }
    operadora.closeFile();
    return valid;
  }

}