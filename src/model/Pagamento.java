 
package model;

import org.joda.time.DateTime;

 
public interface Pagamento {

  interface Builder {

    String getTitular();

    double getValor();

    DateTime getDataDeCriacao();
  }

  String getTitular();

  double getValor();

  DateTime getDataDeCriacao();

}