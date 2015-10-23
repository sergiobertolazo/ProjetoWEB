 
package  controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.AeronaveModel;
import  model.PassagemModel;
import  model.Voo;
import  model.VooModel;
import  util.FlightRequestWrapper;
import  util.JSONObject;

 
@WebServlet(value = "/base/aeronave/del")
public class DeletarAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf8");
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();
    JSONObject obj = new JSONObject();

    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    Integer id = wrapper.intParam("id");

    try {
      List<Voo> voos = new VooModel().consultarPorAeronaveId(id);
      for (Voo voo : voos) {
        new PassagemModel().cancelarPorVoo(voo);
      }
      new VooModel().deletaPorAeronaveId(id);
      new AeronaveModel().deletar(id);
      obj.put("success", "true");
    } catch (SQLException e) {
      obj.put("failure", "true");
      obj.put("exception", e);
      core.logError("SQL Aeronave has failed", e);
    }
    out.print(obj);
  }

}