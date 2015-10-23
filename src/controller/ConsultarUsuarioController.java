 
package  controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import  core.FlightCore;
import  model.Usuario;
import  model.UsuarioModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/usuario")
public class ConsultarUsuarioController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();
  private List<Usuario> usuarios;

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    usuarios = new UsuarioModel().consultar(wrapper);

    req.setAttribute("usuarios", usuarios);
    req.getRequestDispatcher("/usuario-table.jsp").forward(req, res);
  }

  public List<Usuario> getUsuarios() {
    return usuarios;
  }
}