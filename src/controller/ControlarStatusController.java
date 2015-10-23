 
package  controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  model.Status;
import  model.VooModel;
import  util.FlightRequestWrapper;
import  util.JSONObject;

 
@WebServlet(value = "/base/voo/status")
public class ControlarStatusController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf8");
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();
    JSONObject obj = new JSONObject();

    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    int id = wrapper.intParam("id");
    Status status = wrapper.enumParam(Status.class, "statusRes");

    try {
      VooModel vooModel = new VooModel();
      vooModel.controlarStatus(id, status);
      
      obj.put("success", "true");
    } catch (SQLException e) {
      obj.put("failure", "true");
      obj.put("exception", e);
    }
    out.print(obj);
  }
  
}