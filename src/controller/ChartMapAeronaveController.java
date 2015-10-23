 
package  controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  model.Aeronave;
import  model.AeronaveModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/aeronave/map/chart")
public class ChartMapAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    Aeronave aeronave = new AeronaveModel().consultarPorId(wrapper.intParam("id"));

    InputStream is;
    try {
      is = aeronave.getCode();
      byte[] buf = new byte[32 * 1024];
      int nRead = 0;
      resp.setContentType("image/jpeg");
      while ((nRead = is.read(buf)) != -1) {
        resp.getOutputStream().write(buf, 0, nRead);
      }
      resp.getOutputStream().flush();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}