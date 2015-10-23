 
package model;

import org.joda.time.DateTime;

 
public class PagamentoModel implements Pagamento {

  private final String titular;
  private final double valor;
  private final DateTime dataDeCriacao;

  public PagamentoModel(Builder builder) {
    this.valor = builder.getValor();
    this.dataDeCriacao = builder.getDataDeCriacao();
    this.titular = builder.getTitular();
  }

  @Override
  public String getTitular() {
    return titular;
  }

  @Override
  public double getValor() {
    return valor;
  }

  @Override
  public DateTime getDataDeCriacao() {
    return dataDeCriacao;
  }

}