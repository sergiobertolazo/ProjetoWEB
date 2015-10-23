 
package model;

import java.sql.SQLException;
import java.util.List;

import util.CPF;
import util.FlightRequestWrapper;
import util.RequestParam;

 
public interface Usuario {

  interface Builder extends util.Builder<Usuario> {

    String getCodigo();

    PessoaFisica getPessoaFisica();

    Perfil getPerfil();

    String getLogin();

    String getSenha();
  }

  interface Jdbc {

    void criar(Usuario usuario) throws SQLException;

    List<Usuario> consultar(RequestParam request);

    Usuario consultarPorCpf(CPF cpf);

    Usuario consultarUsuario(RequestParam request, String cript);

    boolean atualizar(Usuario usuario) throws SQLException;

    void deletar(int id) throws SQLException;

    Usuario consultarPorId(int id);

  }

  int getId();

  String getCodigo();

  PessoaFisica getPessoaFisica();

  Perfil getPerfil();

  String getLogin();

  String getSenha();

  void criar(Usuario pojo) throws SQLException;

  List<Usuario> consultar(FlightRequestWrapper request);

  Usuario consultarPorCpf(CPF cpf);

  Usuario consultarUsuario(FlightRequestWrapper request, String cript);

  Usuario consultarPorId(int id);

  void atualizar(Usuario pojo) throws SQLException;

  void deletar(int id) throws SQLException;

}