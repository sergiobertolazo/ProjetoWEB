
package  controller;

import java.sql.ResultSet;

import  jdbc.ResultSetJdbc;
import  jdbc.ResultSetJdbcLoader;
import  jdbc.ResultSetJdbcWrapper;
import  model.Perfil;
import  model.PessoaFisica;
import  model.Usuario;
import  model.UsuarioModel;


public class UsuarioLoader implements ResultSetJdbcLoader<Usuario> {

  private final String alias;

  public UsuarioLoader() {
    this.alias = "USUARIO";
  }

  @Override
  public Usuario get(ResultSet resultSet) {
    ResultSetJdbcWrapper rs = new ResultSetJdbcWrapper(resultSet, alias);
    return new UsuarioBuilder(rs).createInstance();
  }

  private class UsuarioBuilder implements Usuario.Builder {

    private final ResultSetJdbc rs;

    public UsuarioBuilder(ResultSetJdbc rs) {
      this.rs = rs;
    }

    @Override
    public Usuario createInstance() {
      UsuarioModel impl = new UsuarioModel(this);
      impl.setId(rs.getInt("ID"));
      return impl;
    }

    @Override
    public String getCodigo() {
      return rs.getString("CODIGO");
    }

    @Override
    public Perfil getPerfil() {
      int valor = rs.getInt("PERFIL");
      Perfil[] cargos = Perfil.values();
      return cargos[valor];
    }

    @Override
    public String getLogin() {
      return rs.getString("LOGIN");
    }

    @Override
    public String getSenha() {
      return rs.getString("SENHA");
    }

    @Override
    public PessoaFisica getPessoaFisica() {
      ResultSet resultSet = rs.getResultSet();
      return new PessoaFisicaLoader().get(resultSet);
    }

  }

}