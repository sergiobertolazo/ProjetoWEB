 
package  util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

 
public class CopyFile {

  public static boolean copyfile(String srFile, String dtFile) {
    boolean res = false;
    try {
      File f1 = new File(srFile);
      File f2 = new File(dtFile);

      InputStream in = new FileInputStream(f1);
      OutputStream out = new FileOutputStream(f2);

      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();

      res = true;

    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(
          null, "Arquivo não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(
          null, "Arquivo não suportado", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    return res;
  }

}