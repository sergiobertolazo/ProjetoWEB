 
package  controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.Voo;
import  model.VooModel;
import  util.FlightRequestWrapper;
import  util.FormatDateTime;

 
@WebServlet(value = "/base/voo/update")
public class AtualizarVooController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper request = new FlightRequestWrapper(req);
    Voo voo = new VooUpdate(request).createInstance();
    try {
      new VooModel().atualizar(voo);
      req.getRequestDispatcher("/consultar-voo.jsp").forward(req, resp);
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

    Voo voo = new VooModel().consultarPorId(id);
    req.setAttribute("voo_id", voo.getId());
    req.setAttribute("voo_codigo", voo.getCodigo());
    req.setAttribute("voo_partida", FormatDateTime.parseToStringDateTime(voo.getDataDePartida().toString(), req.getLocale().getCountry()));
    req.setAttribute("voo_chegada", FormatDateTime.parseToStringDateTime(voo.getDataDeChegada().toString(), req.getLocale().getCountry()));
    req.setAttribute("voo_observacao", voo.getObservacao());
    req.getRequestDispatcher("/voo-form-update.jsp").forward(req, resp);
  }

}