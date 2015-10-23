
package  controller;

import org.joda.time.DateTime;

import  dao.AeronaveDAO;
import  model.Aeronave;
import  model.Status;
import  model.Voo;
import  model.VooModel;
import  util.FormatDateTime;
import  util.RequestParam;


public class VooCreate implements Voo.Builder {

  private final RequestParam request;

  public VooCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Voo createInstance() {
    if (getDataDePartida().isBefore(getDataDeChegada()) &&
        getDataDePartida().isAfter(System.currentTimeMillis())) {
      return new VooModel(this);
    } else {
      return null;
    }
  }

  @Override
  public String getCodigo() {
    return request.stringParam("codigo");
  }

  @Override
  public Aeronave getAeronave() {
    return new AeronaveDAO().consultarPorId(request.intParam("aeronave"));
  }

  @Override
  public String getOrigem() {
    return request.stringParam("origem");
  }

  @Override
  public String getDestino() {
    return request.stringParam("destino");
  }

  @Override
  public String getEscala() {
    return request.stringParam("escala") != null ? request.stringParam("destino") : "N/D";
  }

  @Override
  public DateTime getDataDePartida() {
    return FormatDateTime.parseToDateTime(request.stringParam("partida"));
  }

  @Override
  public DateTime getDataDeChegada() {
    return FormatDateTime.parseToDateTime(request.stringParam("chegada"));
  }

  @Override
  public String getObservacao() {
    return null;
  }

  @Override
  public Status getStatus() {
    return Status.DISPONIVEL;
  }

  @Override
  public int getAssentoLivre() {
    return getAeronave().getQtdDeAssento();
  }

  @Override
  public double getPreco() {
    return request.doubleParam("preco");
  }

}