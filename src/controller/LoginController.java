
package  controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.Usuario;
import  model.UsuarioModel;
import  util.EncryptPassword;
import  util.FlightRequestWrapper;

 
@WebFilter("*.jsp")
@WebServlet(value = "/base/login")
public class LoginController extends HttpServlet implements Filter {

  private static final long serialVersionUID = 1L;
  private FlightCore core = FlightCore.getInstance();

  @Override
  public void destroy() {
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    // Encriptografia para senha utilizando codigos 'sun.misc64Encoder'
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    EncryptPassword encrypt = new EncryptPassword();
    if (wrapper.stringParam("pass") != null && wrapper.stringParam("login") != null
        && wrapper.stringParam("logout") == null) {
      Usuario usuarioLogado = new UsuarioModel().consultarUsuario(wrapper, encrypt.toEncryptMD5(wrapper.stringParam("pass")));
      core.setUsuarioLogado(usuarioLogado);
      if (usuarioLogado != null)
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
      else
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    } else {
      core.setUsuarioLogado(null);
      req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp,
      FilterChain chain) throws IOException, ServletException {
    core.logInfo("Iniciando autenticação");

    Usuario usuarioLogado = core.getUsuarioLogado();
    if (usuarioLogado != null) {
      core.logInfo("Usuario " + usuarioLogado.getLogin() + " logado");
      chain.doFilter(req, resp);
    } else {
      core.logInfo("Usuario NAO logado");
      req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

}