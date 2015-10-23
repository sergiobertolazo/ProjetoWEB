
package  controller;

import org.joda.time.DateTime;

import  model.Aeronave;
import  model.Status;
import  model.Voo;
import  model.VooModel;
import  util.RequestParam;


public class VooUpdate implements Voo.Builder {

  private final RequestParam request;

  public VooUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Voo createInstance() {
    if (getDataDePartida().isBefore(getDataDeChegada()) &&
        getDataDePartida().isAfter(System.currentTimeMillis())) {
      VooModel impl = new VooModel(this);
      impl.setId(request.intParam("id"));
      return impl;
    } else {
      return null;
    }
  }

  @Override
  public String getCodigo() {
    return null;
  }

  @Override
  public Aeronave getAeronave() {
    return null;
  }

  @Override
  public String getOrigem() {
    return null;
  }

  @Override
  public String getDestino() {
    return null;
  }

  @Override
  public String getEscala() {
    return null;
  }

  @Override
  public DateTime getDataDePartida() {
    return request.dateTimeParam("partida");
  }

  @Override
  public DateTime getDataDeChegada() {
    return request.dateTimeParam("chegada");
  }

  @Override
  public String getObservacao() {
    return request.stringParam("observacao");
  }

  @Override
  public Status getStatus() {
    return request.enumParam(Status.class, "status");
  }

  @Override
  public int getAssentoLivre() {
    return 0;
  }

  @Override
  public double getPreco() {
    return 0;
  }

}