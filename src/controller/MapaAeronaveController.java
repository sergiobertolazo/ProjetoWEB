
package  controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  model.Aeronave;
import  model.AeronaveModel;
import  util.FlightRequestWrapper;

@WebServlet(value = "/base/aeronave/map")
public class MapaAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    Aeronave aeronave = new AeronaveModel().consultarPorId(wrapper.intParam("id"));

    req.setAttribute("aeronave", aeronave);
    req.getRequestDispatcher("/aeronave-map.jsp").forward(req, resp);
  }

}