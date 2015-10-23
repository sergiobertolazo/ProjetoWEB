 
package  controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;

import  core.FlightCore;
import  model.PessoaFisica;
import  model.PessoaFisicaModel;
import  model.Usuario;
import  model.UsuarioModel;
import  util.CPF;
import  util.FlightRequestWrapper;
import  util.FormatDateTime;
import  util.GerarCodigo;
import  util.VerifierString;

 
@WebServlet(value = "/base/usuario/create")
public class CriarUsuarioController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();
  private String codigo;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      FlightRequestWrapper request = new FlightRequestWrapper(req);
      CPF cpf = CPF.parse(request.stringParam("cpf"));

      UsuarioModel usuarioModel = new UsuarioModel();
      Usuario usuario = usuarioModel.consultarPorCpf(cpf);

      if (usuario != null) {
        req.setAttribute("criarUsuarioErro", "criar.usuario.erro");
      } else {
        PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
        PessoaFisica pf = pessoaFisicaModel.consultarPorCPF(cpf);

        if (pf == null) {
          LocalDate date = null;
          if (VerifierString.isBirthDay(request.stringParam("nascimento"), req.getLocale().getCountry())) {
            date = FormatDateTime.parseToLocalDate(request.stringParam("nascimento"));
          } else {
            // TODO MENSAGEM
          }

          request.set("nascimento", date);

          request = new FlightRequestWrapper(req);
          PessoaFisica pojo = new PessoaFisicaCreate(request).createInstance();
          boolean executed = false;
          executed = pessoaFisicaModel.criar(pojo);

          if (executed) {
            PessoaFisica pessoa = pessoaFisicaModel.consultarPorCPF(pojo.getCpf());
            request.set("pessoaFisica", pessoa.getId());
          } else {
            return;
          }

          // Utiliza a PF existente (uma vez cliente)
        } else {
          req.setAttribute("pessoaFisica", pf.getId());
        }
        request = new FlightRequestWrapper(req);
        Usuario pojo = new UsuarioCreate(request).createInstance();
        new UsuarioModel().criar(pojo);
      }

      req.getRequestDispatcher("/consultar-usuario.jsp").forward(req, resp);
    } catch (Exception e) {
      core.logError("Error", e);
    }
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    codigo = new GerarCodigo("USUARIO").getCodigo();
    req.setAttribute("codigo", codigo);
    req.getRequestDispatcher("/codigo.jsp").forward(req, resp);
  }

  public String getCodigo() {
    return codigo;
  }
}