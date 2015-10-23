
package  controller;

import  model.Perfil;
import  model.PessoaFisica;
import  model.Usuario;
import  model.UsuarioModel;
import  util.RequestParam;


public class UsuarioUpdate implements Usuario.Builder {

  private final RequestParam request;

  public UsuarioUpdate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Usuario createInstance() {
    UsuarioModel impl = new UsuarioModel(this);
    impl.setId(request.intParam("usuario_id"));
    return impl;
  }

  @Override
  public String getCodigo() {
    return null;
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return null;
  }

  @Override
  public Perfil getPerfil() {
    return request.enumParam(Perfil.class, "perfil");
  }

  @Override
  public String getLogin() {
    return request.stringParam("login");
  }

  @Override
  public String getSenha() {
    return request.stringParam("senha");
  }

}