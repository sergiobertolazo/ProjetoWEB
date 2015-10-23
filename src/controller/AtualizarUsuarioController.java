 
package  controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  model.Usuario;
import  model.UsuarioModel;
import  util.FlightRequestWrapper;
import  util.FormatDateTime;

 
@WebServlet(value = "/base/usuario/update")
public class AtualizarUsuarioController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper request = new FlightRequestWrapper(req);
    Usuario usuario = new UsuarioUpdate(request).createInstance();
    PessoaFisica pf = new PessoaFisicaUpdate(request).createInstance();
    try {
      new UsuarioModel().atualizar(usuario);
      new PessoaFisicaModel().atualizar(pf);
      req.getRequestDispatcher("/consultar-usuario.jsp").forward(req, resp);
    } catch (Exception e) {
      core.logError("SQL Error", e);
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/erro.jsp").forward(req, resp);
    }

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    Integer id = wrapper.intParam("id");

    Usuario usuario = new UsuarioModel().consultarPorId(id);
    PessoaFisica pf = usuario.getPessoaFisica();
    req.setAttribute("usuario_id", usuario.getId());
    req.setAttribute("usuario_codigo", usuario.getCodigo());
    req.setAttribute("pf_id", pf.getId());
    req.setAttribute("pf_nome", pf.getNome());
    req.setAttribute("pf_sobrenome", pf.getSobrenome());
    req.setAttribute("pf_nascimento", FormatDateTime.parseToStringLocalDate(pf.getDataNascimento().toString(), req.getLocale().getCountry()));
    req.setAttribute("pf_cpf", pf.getCpf().toString());
    req.setAttribute("pf_rg", pf.getRg());
    req.setAttribute("pf_endereco", pf.getEndereco());
    req.setAttribute("pf_telResidencial", pf.getTelResidencial());
    req.setAttribute("pf_telCelular", pf.getTelCelular());
    req.setAttribute("pf_email", pf.getEmail());
    req.setAttribute("usuario_perfil", usuario.getPerfil());
    req.setAttribute("usuario_login", usuario.getLogin());
    req.setAttribute("ussuario_senha", usuario.getSenha());
    req.getRequestDispatcher("/usuario-form-update.jsp").forward(req, resp);
  }
}