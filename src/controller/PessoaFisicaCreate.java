
package  controller;

import org.joda.time.LocalDate;

import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  util.CPF;
import  util.RequestParam;

public class PessoaFisicaCreate implements PessoaFisica.Builder {

  private final RequestParam request;

  public PessoaFisicaCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public PessoaFisica createInstance() {
    return new PessoaFisicaModel(this);
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
    return CPF.parse(request.stringParam("cpf"));
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