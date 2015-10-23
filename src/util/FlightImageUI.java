 
package  util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

 
// Este classe deixa as view dinâmicas,
// inserindo imagens e removendo-as a cada focusLost
public class FlightImageUI {

  public static void add(JLabel imagem,
                         JLabel alerta,
                         String mensagem,
                         ResourceBundle bundle,
                         JPanel conteudo) {
    try {
      InputStream stream = FlightImageUI.class.getResourceAsStream("/img/icon_disponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.BLACK);
      alerta.setText(mensagem);

      conteudo.add(imagem);
      conteudo.add(alerta);

    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

  public static void addError(JLabel imagem,
                              JLabel alerta,
                              String mensagem,
                              ResourceBundle bundle,
                              JPanel conteudo) {
    try {
      InputStream stream = FlightImageUI.class.getResourceAsStream("/img/icon_indisponivel.png");
      Image image = ImageIO.read(stream);
      ImageIcon icon = new ImageIcon(image);
      imagem.setIcon(icon);

      alerta.setFont(new Font("Arial", Font.BOLD, 13));
      alerta.setForeground(Color.RED);
      alerta.setText(mensagem);

      conteudo.add(imagem);
      conteudo.add(alerta);

    } catch (IOException e) {
      ErrorSystem.addException(e, bundle);
    }
  }

}