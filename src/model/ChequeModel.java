 
package model;

 
public class ChequeModel extends PagamentoModel implements Cheque {

  private final int numero;
  private final int banco;
  private final int agencia;
  private final int conta;

  public ChequeModel(Cheque.Builder builder) {
    super(builder);
    this.numero = builder.getNumero();
    this.banco = builder.getBanco();
    this.agencia = builder.getAgencia();
    this.conta = builder.getConta();
  }

  @Override
  public int getNumero() {
    return numero;
  }

  @Override
  public int getBanco() {
    return banco;
  }

  @Override
  public int getAgencia() {
    return agencia;
  }

  @Override
  public int getConta() {
    return conta;
  }

}