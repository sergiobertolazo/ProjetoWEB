
package  util;

import javax.swing.JPanel;


public abstract class AbstractFlightUI {

  protected abstract JPanel getConteudo();

  protected abstract void mainMenu();

  protected void refresh() {
    getConteudo().removeAll();
    repaint();
  }

  protected void repaint() {
    getConteudo().validate();
    getConteudo().repaint();
  }

}