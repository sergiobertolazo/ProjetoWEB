 
package  controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.Voo;
import  model.VooModel;
import  util.FlightRequestWrapper;
import  util.GerarCodigo;

 
@WebServlet(value = "/base/voo/create")
public class CriarVooController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();
  private String codigo;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper request = new FlightRequestWrapper(req);

    Voo pojo = new VooCreate(request).createInstance();
    try {
      new VooModel().criar(pojo);
      req.getRequestDispatcher("/consultar-voo.jsp").forward(req, resp);
    } catch (SQLException e) {
      core.logError("SQL Error", e);
    }

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    codigo = new GerarCodigo("VOO").getCodigo();
    req.setAttribute("codigo", codigo);
    req.getRequestDispatcher("/codigo.jsp").forward(req, resp);
  }

  public String getCodigo() {
    return codigo;
  }

}