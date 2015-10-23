 
package  controller;

import org.joda.time.LocalDate;

import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  util.CPF;
import  util.RequestParam;
 
public class PessoaFisicaUpdate implements PessoaFisica.Builder {

  private final RequestParam request;

  public PessoaFisicaUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public PessoaFisica createInstance() {
    PessoaFisicaModel impl = new PessoaFisicaModel(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public String getNome() {
    return request.stringParam("nome");
  }

  @Override
  public String getSobrenome() {
    return request.stringParam("sobrenome");
  }

  @Override
  public LocalDate getDataNascimento() {
    return request.localDateParam("nascimento");
  }

  @Override
  public CPF getCpf() {
    String value = request.stringParam("cpf");
    return CPF.parse(value);
  }

  @Override
  public String getRg() {
    return request.stringParam("rg");
  }

  @Override
  public String getEndereco() {
    return request.stringParam("endereco");
  }

  @Override
  public long getTelResidencial() {
    return request.longParam("telResidencial");
  }

  @Override
  public long getTelCelular() {
    return request.longParam("telCelular");
  }

  @Override
  public String getEmail() {
    return request.stringParam("email");
  }

}