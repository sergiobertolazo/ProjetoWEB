 
package  controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import  core.FlightCore;
import  model.FormaDeTratamento;
import  model.Passagem;
import  model.PassagemModel;
import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  model.Status;
import  model.Tipo;
import  model.Voo;
import  model.VooModel;
import  util.CPF;
import  util.FlightRequestWrapper;
import  util.GerarCodigo;
import  util.RequestParamWrapper;


@WebServlet(value = "/base/passagem/vender")
public class VenderPassagemController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    FlightRequestWrapper wrapper = new FlightRequestWrapper(req);
    String pageConsulta = wrapper.stringParam("id");
    if (pageConsulta == null) {
      RequestParamWrapper request = new RequestParamWrapper();

      request.set("status", Status.DISPONIVEL);
      request.set("partida", wrapper.dateTimeParam("partida"));
      request.set("chegada", wrapper.dateTimeParam("chegada"));
      request.set("origem", wrapper.stringParam("origem"));
      request.set("destino", wrapper.stringParam("destino"));
      List<Voo> voos = new VooModel().consultar(request);

      req.setAttribute("voos", voos);
      req.getRequestDispatcher("/passagem-table.jsp").forward(req, resp);
    } else {
      Voo voo = new VooModel().consultarPorId(wrapper.intParam("id"));
      String codigo = new GerarCodigo("PASSAGEM").getCodigo();
      req.setAttribute("codigo", codigo);
      req.setAttribute("adulto", Tipo.ADULTO);
      req.setAttribute("crianca", Tipo.CRIANCA);
      req.setAttribute("bebe", Tipo.BEBE);

      req.setAttribute("sr", FormaDeTratamento.SR);
      req.setAttribute("sra", FormaDeTratamento.SRA);
      req.setAttribute("srta", FormaDeTratamento.SRTA);

      req.setAttribute("voo", voo);
      req.getRequestDispatcher("/passagem-form-vender.jsp").forward(req, resp);
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    FlightRequestWrapper request = new FlightRequestWrapper(req);
    PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
    PessoaFisica pf = pessoaFisicaModel.consultarPorCPF(CPF.parse(request.stringParam("cpf")));

    if (pf != null) {
      core.logInfo("Passage already sold for this customer");
      req.setAttribute("exception", "Passage already sold for this customer");
      req.getRequestDispatcher("/erro.jsp").forward(req, resp);
    } else {
      pf = new PessoaFisicaCreate(request).createInstance();
      try {
        new PessoaFisicaModel().criar(pf);
      } catch (SQLException e) {
        core.logError("SQL Error", e);
        req.setAttribute("exception", e);
        req.getRequestDispatcher("/erro.jsp").forward(req, resp);
      }
    }

    pf = new PessoaFisicaModel().consultarPorCPF(CPF.parse(request.stringParam("cpf")));
    RequestParamWrapper wrapper = new RequestParamWrapper();
    wrapper.set("voo", request.intParam("vooId"));
    wrapper.set("pessoaFisica", pf.getId());
    wrapper.set("codBilhete", request.stringParam("codigo"));

    Passagem pojo = new PassagemCreate(wrapper).createInstance();
    boolean executed = false;
    try {
      executed = new PassagemModel().vender(pojo);
    } catch (SQLException e) {
      core.logError("SQL Error", e);
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/erro.jsp").forward(req, resp);
    }

    if (executed) {
      try {
        Voo voo = new VooModel().consultarPorId(request.intParam("vooId"));
        new VooModel().decrementarAssento(request.intParam("vooId"));
        req.setAttribute("voo", voo);
        req.setAttribute("pf", pf);
        req.setAttribute("preco", request.doubleParam("valor"));
        req.getRequestDispatcher("/emitir-bilhete.jsp").forward(req, resp);
      } catch (SQLException e) {
        core.logError("SQL Error", e);
        req.setAttribute("exception", e);
        req.getRequestDispatcher("/erro.jsp").forward(req, resp);
      }
    }
  }

}