 
package model;

import java.util.List;

import org.joda.time.DateTime;

import util.RequestParam;

 
public class VooFake implements Voo {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Aeronave getAeronave() {
    throw new UnsupportedOperationException();

  }

  @Override
  public String getOrigem() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getDestino() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getEscala() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getDataDePartida() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DateTime getDataDeChegada() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getObservacao() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Status getStatus() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getAssentoLivre() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getPreco() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultaPainel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean criar(Voo pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultar(RequestParam request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deletar(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean atualizar(Voo pojo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void controlarStatus(int id, Status fromString) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void decrementarAssento(int vooId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deletaPorAeronaveId(int aeronaveId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Voo> consultarPorAeronaveId(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void incrementarAssento(int vooId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Voo consultarPorId(int id) {
    throw new UnsupportedOperationException();
  }

}