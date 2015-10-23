
package  controller;

import java.io.InputStream;

import  model.Aeronave;
import  model.AeronaveModel;
import  util.RequestParam;


public class AeronaveUpdate implements Aeronave.Builder {

  private final RequestParam request;

  public AeronaveUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Aeronave createInstance() {
    AeronaveModel impl = new AeronaveModel(this);
    impl.setId(request.intParam("id"));
    return impl;
  }

  @Override
  public String getCodigo() {
    return null;
  }

  @Override
  public String getNome() {
    return request.stringParam("nome");
  }

  @Override
  public int getQtdDeAssento() {
    return 0;
  }

  @Override
  public boolean isMapa() {
    return false;
  }

  @Override
  public InputStream getCode() {
    return null;
  }

}