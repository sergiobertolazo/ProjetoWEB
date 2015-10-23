 
package  controller;

import  model.Perfil;
import  model.PessoaFisica;
import  model.PessoaFisicaFake;
import  model.Usuario;
import  model.UsuarioModel;
import  util.RequestParam;

 
public class UsuarioCreate implements Usuario.Builder {

  private final RequestParam request;

  public UsuarioCreate(RequestParam request) {
    this.request = request;
  }

  @Override
  public Usuario createInstance() {
    return new UsuarioModel(this);
  }

  @Override
  public String getCodigo() {
    return request.stringParam("codigo");
  }

  @Override
  public PessoaFisica getPessoaFisica() {
    return new PessoaFisicaFake() {
      @Override
      public int getId() {
        return request.intParam("pessoaFisica");
      }
    };
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