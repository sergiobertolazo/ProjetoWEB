
package  controller;

import java.io.InputStream;

import  model.Aeronave;
import  model.AeronaveModel;
import  util.RequestParam;

public class AeronaveCreate implements Aeronave.Builder {

  private final RequestParam request;
  private final InputStream inputStream;

  public AeronaveCreate(RequestParam request, InputStream inputStream) {
    this.request = request;
    this.inputStream = inputStream;
  }

  @Override
  public Aeronave createInstance() {
    return new AeronaveModel(this);
  }

  @Override
  public String getCodigo() {
    return request.stringParam("codigo");
  }

  @Override
  public String getNome() {
    return request.stringParam("nome");
  }

  @Override
  public int getQtdDeAssento() {
    return request.intParam("qtdDeAssento");
  }

  @Override
  public boolean isMapa() {
    return true;
  }

  @Override
  public InputStream getCode() {
    return inputStream;
  }

}