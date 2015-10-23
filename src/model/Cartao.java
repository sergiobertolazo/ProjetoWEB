
package model;

import org.joda.time.LocalDate;


public interface Cartao extends Pagamento {

  interface Builder extends Pagamento.Builder,
      util.Builder<Cartao> {

    long getNumero();

    LocalDate getDataDeValidade();

    Bandeira getBandeira();

  }

  interface jdbc {

    boolean debitar();

    boolean creditar(Cartao cartao);

  }

  long getNumero();

  LocalDate getDataDeValidade();

  Bandeira getBandeira();

}