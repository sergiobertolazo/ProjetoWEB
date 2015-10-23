
package  model;

import org.joda.time.LocalDate;


public class CartaoModel extends PagamentoModel implements Cartao {

  private final long numero;
  private final LocalDate dataDeValidade;
  private final Bandeira bandeira;

  public CartaoModel(Cartao.Builder builder) {
    super(builder);
    this.numero = builder.getNumero();
    this.dataDeValidade = builder.getDataDeValidade();
    this.bandeira = builder.getBandeira();
  }

  @Override
  public long getNumero() {
    return numero;
  }

  @Override
  public LocalDate getDataDeValidade() {
    return dataDeValidade;
  }

  @Override
  public Bandeira getBandeira() {
    return bandeira;
  }

}