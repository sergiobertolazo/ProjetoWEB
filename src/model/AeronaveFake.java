 
package  model;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import  util.RequestParam;

 
public class AeronaveFake implements Aeronave {

  @Override
  public int getId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getCodigo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getNome() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getQtdDeAssento() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isMapa() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void criar(Aeronave pojo, File image) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean deletar(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void atualizar(Aeronave aeronave) {
    throw new UnsupportedOperationException();
  }

  @Override
  public InputStream getCode() throws SQLException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Aeronave> consultar(RequestParam request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Aeronave consultarPorId(int id) {
    throw new UnsupportedOperationException();
  }

}