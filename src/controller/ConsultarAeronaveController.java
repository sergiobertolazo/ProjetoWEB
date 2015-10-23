 
package  controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import  model.Aeronave;
import  model.AeronaveModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/aeronave")
public class ConsultarAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private List<Aeronave> aeronaves;

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    aeronaves = new AeronaveModel().consultar(wrapper);

    req.setAttribute("aeronaves", aeronaves);
    req.getRequestDispatcher("/aeronave-table.jsp").forward(req, res);
  }

  public List<Aeronave> getAeronaves() {
    return aeronaves;
  }

}