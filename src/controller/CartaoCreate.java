 
package  controller;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import  model.Bandeira;
import  model.Cartao;
import  model.CartaoModel;
import  util.RequestParam;

 
public class CartaoCreate implements Cartao.Builder {

  private final RequestParam request;

  public CartaoCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Cartao createInstance() {
    return new CartaoModel(this);
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public long getNumero() {
    return request.longParam("numero");
  }

  @Override
  public LocalDate getDataDeValidade() {
    return request.localDateParam("validade").withDayOfMonth(1);
  }

  @Override
  public Bandeira getBandeira() {
    return request.enumParam(Bandeira.class, "bandeira");
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