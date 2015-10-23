 
package model;

import java.sql.SQLException;
import java.util.List;

import dao.UsuarioDAO;
import jdbc.Database;
import jdbc.FactoryJdbc;
import util.CPF;
import util.FlightRequestWrapper;

 
public class UsuarioModel implements Usuario {

  private int id;
  private String codigo;
  private PessoaFisica pessoaFisica;
  private Perfil perfil;
  private String login;
  private String senha;
  private UsuarioDAO dao;

  public UsuarioModel(Builder builder) {
    this.codigo = builder.getCodigo();
    this.pessoaFisica = builder.getPessoaFisica();
    this.perfil = builder.getPerfil();
    this.login = builder.getLogin();
    this.senha = builder.getSenha();
  }

  public UsuarioModel() {
    Database database = FactoryJdbc.getInstance().getDatabase();
    if(database.equals(Database.MYSQL))
      dao = new UsuarioDAO();
    if(database.equals(Database.ORACLE))
      dao = null;
    if(database.equals(Database.SQL_SERVER))
      dao = null;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getCodigo() {
    return codigo;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return pessoaFisica;
  }

  @Override
  public Perfil getPerfil() {
    return perfil;
  }

  @Override
  public String getLogin() {
    return login;
  }

  @Override
  public String getSenha() {
    return senha;
  }

  @Override
  public List<Usuario> consultar(FlightRequestWrapper request) {
    return dao.consultar(request);
  }

  @Override
  public void deletar(int id) throws SQLException {
    dao.deletar(id);
  }

  @Override
  public void atualizar(Usuario pojo) throws SQLException {
    dao.atualizar(pojo);
  }

  @Override
  public Usuario consultarUsuario(FlightRequestWrapper request, String cript) {
    return dao.consultarUsuario(request, cript);
  }

  @Override
  public void criar(Usuario pojo) throws SQLException {
    dao.criar(pojo);
  }

  @Override
  public Usuario consultarPorCpf(CPF cpf) {
    return dao.consultarPorCpf(cpf);
  }

  @Override
  public Usuario consultarPorId(int id) {
    return dao.consultarPorId(id);
  }

}