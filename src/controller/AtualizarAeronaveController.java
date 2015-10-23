 
package  controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.Aeronave;
import  model.AeronaveModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/aeronave/update")
public class AtualizarAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper request = new FlightRequestWrapper(req);
    Aeronave aeronave = new AeronaveUpdate(request).createInstance();
    try {
      new AeronaveModel().atualizar(aeronave);
      req.getRequestDispatcher("/consultar-aeronave.jsp").forward(req, resp);
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

    Aeronave aeronave = new AeronaveModel().consultarPorId(id);
    req.setAttribute("aeronave", aeronave);
    req.getRequestDispatcher("/aeronave-form-update.jsp").forward(req, resp);
  }

}