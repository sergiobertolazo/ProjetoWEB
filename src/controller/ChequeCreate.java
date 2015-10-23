 
package  controller;

import org.joda.time.DateTime;

import  model.Cheque;
import  model.ChequeModel;
import  util.RequestParam;

 
public class ChequeCreate implements Cheque.Builder {

  private final RequestParam request;

  public ChequeCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Cheque createInstance() {
    return new ChequeModel(this);
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public int getNumero() {
    return request.intParam("numero");
  }

  @Override
  public int getBanco() {
    return request.intParam("banco");
  }

  @Override
  public int getAgencia() {
    return request.intParam("agencia");
  }

  @Override
  public int getConta() {
    return request.intParam("conta");
  }

  @Override
  public double getValor() {
    return request.doubleParam("valor");
  }

  @Override
  public DateTime getDataDeCriacao() {
    return new DateTime();
  }

}