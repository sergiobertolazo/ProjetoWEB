 
package  util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

 
// Esta classe tem o unico intuito de diminuir linhas de codigo,
// pois utilizamos muitas vezes o KeyListener
public abstract class FlightKeyPressedListener implements KeyListener {

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}