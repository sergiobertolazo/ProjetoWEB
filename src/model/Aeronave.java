 
package model;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import util.RequestParam;

 
public interface Aeronave {

  interface Builder extends util.Builder<Aeronave> {

    String getCodigo();

    String getNome();

    int getQtdDeAssento();

    boolean isMapa();

    InputStream getCode();

  }

  interface Jdbc {

    void criar(Aeronave aeronave, File image) throws SQLException;

    List<Aeronave> consultar(RequestParam request);

    Aeronave consultarPorId(int id);

    void atualizar(Aeronave aeronave) throws SQLException;

    boolean deletar(int id) throws SQLException;

  }

  int getId();

  String getCodigo();

  String getNome();

  int getQtdDeAssento();

  boolean isMapa();

  InputStream getCode() throws SQLException;

  void criar(Aeronave pojo, File image) throws SQLException;

  List<Aeronave> consultar(RequestParam request);

  Aeronave consultarPorId(int id);

  boolean deletar(int id) throws SQLException;

  void atualizar(Aeronave aeronave) throws SQLException;

}