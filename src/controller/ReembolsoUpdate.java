
package  controller;

import  model.Passagem;
import  model.Reembolso;
import  model.ReembolsoModel;
import  util.CPF;
import  util.RequestParam;


public class ReembolsoUpdate implements Reembolso.Builder {

  private final RequestParam request;

  public ReembolsoUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Reembolso createInstance() {
    ReembolsoModel impl = new ReembolsoModel(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public Passagem getPassagem() {
    return null;
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public CPF getCpf() {
    Long value = request.longParam("cpf");
    return CPF.valueOf(value);
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
}