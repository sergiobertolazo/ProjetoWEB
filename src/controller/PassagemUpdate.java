 
package  controller;

import  model.Passagem;
import  model.PassagemModel;
import  model.PessoaFisica;
import  model.Voo;

 
public class PassagemUpdate implements Passagem.Builder {

  private final Passagem passagem;
  private final Voo voo;

  public PassagemUpdate(Passagem passagem, Voo voo) {
    this.passagem = passagem;
    this.voo = voo;
  }

  @Override
  public Passagem createInstance() {
    PassagemModel impl = new PassagemModel(this);
    impl.setId(passagem.getId());
    return impl;
  }

  @Override
  public Voo getVoo() {
    return voo;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return null;
  }

  @Override
  public String getCodigoBilhete() {
    return null;
  }

  @Override
  public String getAssento() {
    return null;
  }

}