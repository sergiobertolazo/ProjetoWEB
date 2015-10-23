
package  controller;

import  model.Passagem;
import  model.PassagemModel;
import  model.PessoaFisica;
import  model.PessoaFisicaFake;
import  model.Voo;
import  model.VooFake;
import  util.RequestParam;


public class PassagemCreate implements Passagem.Builder {

  private final RequestParam request;

  public PassagemCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Passagem createInstance() {
    return new PassagemModel(this);
  }

  @Override
  public Voo getVoo() {
    return new VooFake() {
      @Override
      public int getId() {
        return request.intParam("voo");
      }
    };
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return new PessoaFisicaFake() {
      @Override
      public int getId() {
        return request.intParam("pessoaFisica");
      }
    };
  }

  @Override
  public String getCodigoBilhete() {
    return request.stringParam("codBilhete");
  }

  @Override
  public String getAssento() {
    return null;
  }

}