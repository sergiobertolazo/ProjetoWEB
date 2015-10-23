 
package  controller;

import  model.Passagem;
import  model.PassagemFake;
import  model.Reembolso;
import  model.ReembolsoModel;
import  util.CPF;
import  util.RequestParam;

 
public class ReembolsoCreate implements Reembolso.Builder {

  private final RequestParam request;

  public ReembolsoCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Reembolso createInstance() {
    return new ReembolsoModel(this);
  }

  @Override
  public Passagem getPassagem() {
    return new PassagemFake() {

      @Override
      public int getId() {
        return request.intParam("passagem");
      }
    };
  }

  @Override
  public String getTitular() {
    return request.stringParam("titular");
  }

  @Override
  public CPF getCpf() {
    String value = request.stringParam("cpf");
    return CPF.parse(value);
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
    return Double.parseDouble(request.stringParam("valor").replace(",", "."));
  }

}