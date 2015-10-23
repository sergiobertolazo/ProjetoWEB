 
package  controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.PassagemModel;
import  model.Voo;
import  model.VooModel;
import  util.FlightRequestWrapper;
import  util.JSONObject;

 
@WebServlet(value = "/base/voo/del")
public class DeletarVooController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf8");
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();
    JSONObject obj = new JSONObject();

    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    int id = wrapper.intParam("id");

    try {
      PassagemModel passagemModel = new PassagemModel();
      VooModel vooModel = new VooModel();

      Voo pojo = vooModel.consultarPorId(id);
      passagemModel.cancelarPorVoo(pojo);
      vooModel.deletar(pojo.getId());

      obj.put("success", "true");
    } catch (SQLException e) {
      obj.put("failure", "true");
      obj.put("exception", e);
      core.logError("SQL voo has failed", e);
    }
    out.print(obj);
  }

}