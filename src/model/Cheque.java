
package model;

 
public interface Cheque extends Pagamento {

  interface Builder extends Pagamento.Builder,
      util.Builder<Cheque> {

    int getNumero();

    int getBanco();

    int getAgencia();

    int getConta();

  }

  int getNumero();

  int getBanco();

  int getAgencia();

  int getConta();

}