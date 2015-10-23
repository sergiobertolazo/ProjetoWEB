 
package model;

import org.joda.time.LocalDate;

import util.CPF;

 
public class PessoaFisicaFake implements PessoaFisica {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getSobrenome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public LocalDate getDataNascimento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public CPF getCpf() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getRg() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEndereco() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTelResidencial() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTelCelular() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEmail() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean criar(PessoaFisica pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void atualizar(PessoaFisica pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PessoaFisica consultarPorCPF(CPF cpf) {
    throw new UnsupportedOperationException();
  }

}