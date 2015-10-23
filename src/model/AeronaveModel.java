 
package  model;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import  dao.AeronaveDAO;
import  jdbc.Database;
import  jdbc.FactoryJdbc;
import  util.RequestParam;

 
public class AeronaveModel implements Aeronave {

  private int id;
  private String codigo;
  private String nome;
  private int qtdDeAssento;
  private boolean mapa;
  private AeronaveDAO dao;
  private InputStream code;

  public AeronaveModel(Builder builder) {
    this.codigo = builder.getCodigo();
    this.nome = builder.getNome();
    this.qtdDeAssento = builder.getQtdDeAssento();
    this.mapa = builder.isMapa();
    this.code = builder.getCode();
  }

  public AeronaveModel() {
    Database database = FactoryJdbc.getInstance().getDatabase();
    if(database.equals(Database.MYSQL))
      dao = new AeronaveDAO();
    if(database.equals(Database.ORACLE))
      dao = null;
    if(database.equals(Database.SQL_SERVER))
      dao = null;
  }

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getCodigo() {
    return codigo;
  }

  @Override
  public String getNome() {
    return nome;
  }

  @Override
  public int getQtdDeAssento() {
    return qtdDeAssento;
  }

  @Override
  public boolean isMapa() {
    return mapa;
  }

  @Override
  public InputStream getCode() throws SQLException {
    return code;
  }

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public void criar(Aeronave pojo, File image) throws SQLException {
    dao.criar(pojo, image);
  }

  @Override
  public List<Aeronave> consultar(RequestParam request) {
    return dao.consultar(request);
  }

  @Override
  public Aeronave consultarPorId(int id) {
    return dao.consultarPorId(id);
  }

  @Override
  public void atualizar(Aeronave pojo) throws SQLException {
    dao.atualizar(pojo);
  }

  @Override
  public boolean deletar(int id) throws SQLException {
    return dao.deletar(id);
  }

}