 
package  util;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

 
public class ErrorSystem {

  public static void addException(Exception e, ResourceBundle bundle) {
    JOptionPane.showMessageDialog(null,
        bundle.getString("erro.sistema.administrador") + "\n\n[ERROR] " + e.getMessage(),
        bundle.getString("erro.sistema.titulo"),
        JOptionPane.ERROR_MESSAGE);
  }

}