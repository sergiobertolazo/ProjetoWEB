 
package  controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import  core.FlightCore;
import  model.Voo;
import  model.VooModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/voo")
public class ConsultarVooController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();
  private List<Voo> voos;

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    voos = new VooModel().consultar(wrapper);

    req.setAttribute("voos", voos);
    req.getRequestDispatcher("/voo-table.jsp").forward(req, res);
  }

  public List<Voo> getVoos() {
    return voos;
  }

}