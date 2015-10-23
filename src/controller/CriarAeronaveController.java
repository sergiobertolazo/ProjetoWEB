 
package  controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import  core.FlightCore;
import  model.Aeronave;
import  model.AeronaveModel;
import  util.GerarCodigo;
import  util.RequestParamWrapper;

 
@WebServlet(value = "/base/aeronave/create")
public class CriarAeronaveController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final FlightCore core = FlightCore.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestParamWrapper request = new RequestParamWrapper();
    File uploadedFile = null;
    InputStream inputStream = null;

    // Busca parametros do request
    boolean isMultipart = ServletFileUpload.isMultipartContent(req);

    if (isMultipart) {
      FileItemFactory factory = new DiskFileItemFactory();

      // Cria um handler de upload
      ServletFileUpload upload = new ServletFileUpload(factory);

      try {
        // Parse de request
        List<FileItem> items = upload.parseRequest(req);
        Iterator<FileItem> iterator = items.iterator();
        while (iterator.hasNext()) {
          FileItem item = iterator.next();
          if (!item.isFormField()) {
            String fileName = item.getName();
            uploadedFile = new File(fileName);
            // Validando extensão de arquivos
            boolean accept = accept(uploadedFile.getName());
            item.write(uploadedFile);
            inputStream = new FileInputStream(uploadedFile);

            core.logInfo("Create file tmp [" + uploadedFile.getAbsolutePath() + "]");
            if (accept) {
              Aeronave aeronave = new AeronaveCreate(request, inputStream).createInstance();
              try {
                new AeronaveModel().criar(aeronave, uploadedFile);
                req.getRequestDispatcher("/consultar-aeronave.jsp").forward(req, resp);
              } catch (Exception e) {
                core.logError("SQL Error", e);
                req.setAttribute("exception", e);
                req.getRequestDispatcher("/erro.jsp").forward(req, resp);
              }
            } else {
              core.logInfo("Extension File not allow, insert a [.jpg], [.png] or [.jpeg]");
              req.setAttribute("exception", "Extension File not allow, insert a [.jpg], [.png] or [.jpeg]");
              req.getRequestDispatcher("/erro.jsp").forward(req, resp);
            }
            uploadedFile.delete();
            core.logInfo("Deleted file tmp [" + uploadedFile.getAbsolutePath() + "]");
          } else {
            if (item.getFieldName().equals("qtdDeAssento"))
              request.set(item.getFieldName(), Integer.parseInt(item.getString()));
            else
              request.set(item.getFieldName(), item.getString());
          }
        }

      } catch (Exception e) {
        core.logError("Generic Error", e);
        req.setAttribute("exception", "Error on server " + e);
        req.getRequestDispatcher("/erro.jsp").forward(req, resp);
      }
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String codigo = new GerarCodigo("AERONAVE").getCodigo();
    req.setAttribute("codigo", codigo);
    req.getRequestDispatcher("/codigo.jsp").forward(req, resp);
  }

  public boolean accept(String name) {
    return name.toLowerCase().endsWith(".jpg") ||
        name.toLowerCase().endsWith(".png") ||
        name.toLowerCase().endsWith(".jpeg");
  }

}