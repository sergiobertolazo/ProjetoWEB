 
package model;

import util.CPF;

 
public class ReembolsoFake implements Reembolso {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Passagem getPassagem() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getTitular() {
    throw new UnsupportedOperationException();
  }

  @Override
  public CPF getCpf() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getBanco() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getAgencia() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getConta() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getValor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean criar(Reembolso reembolso) {
    throw new UnsupportedOperationException();
  }

}