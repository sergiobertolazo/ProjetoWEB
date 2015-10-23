 
package  controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.Passagem;
import  model.PassagemModel;
import  model.Reembolso;
import  model.ReembolsoModel;
import  model.VooModel;
import  util.FlightRequestWrapper;

 
@WebServlet(value = "/base/passagem/cancelar")
public class CancelarPassagemController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PassagemModel passagemModel = new PassagemModel();

    FlightRequestWrapper request = new FlightRequestWrapper(req);
    String codBilhete = request.stringParam("bilhete");

    Passagem passagem = passagemModel.consultarPorCodigoBilhete(codBilhete);
    if (passagem != null) {
      String voo = passagem.getVoo().getCodigo();
      if (voo != null) {
        PassagemModel pasModel = new PassagemModel();
        double reembolso = pasModel.getPreco(passagem);

        if (reembolso > 0.0) {
          req.setAttribute("valor", String.format("%.2f", reembolso));
          req.setAttribute("passagem", passagem);
        } else if (reembolso == 0.0) {
          req.setAttribute("passagem", passagem);
          req.setAttribute("response", "zero");
        } else {
          req.setAttribute("response", "finished");
        }
      } else {
        req.setAttribute("response", "canceled");
      }
    } else {
      req.setAttribute("response", "notFound");
    }
    req.getRequestDispatcher("/passagem-form-cancelar.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ReembolsoModel model = new ReembolsoModel();
    PassagemModel modelPassagem = new PassagemModel();
    FlightRequestWrapper request = new FlightRequestWrapper(req);

    if (request.stringParam("valor") != null) {
      Reembolso reembolso = new ReembolsoCreate(request).createInstance();
      try {
        model.criar(reembolso);
      } catch (SQLException e) {
        core.logError("Generic Error", e);
        req.setAttribute("exception", "Error on server " + e);
        req.getRequestDispatcher("/erro.jsp").forward(req, resp);
      }
    }

    try {
      Passagem _passagem = modelPassagem.consultarPorId(request.intParam("passagem"));
      modelPassagem.efetuarCancelamento(_passagem);
      new VooModel().incrementarAssento(_passagem.getVoo().getId());
      req.getRequestDispatcher("/cancelar-passagem.jsp").forward(req, resp);

    } catch (SQLException e) {
      core.logError("Generic Error", e);
      req.setAttribute("exception", "Error on server " + e);
      req.getRequestDispatcher("/erro.jsp").forward(req, resp);
    }
  }

}