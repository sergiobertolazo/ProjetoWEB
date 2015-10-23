 
package  util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

 
// Esta classe tem o unico intuito de diminuir linhas de codigo,
// pois utilizamos muitas vezes o FocusListener sem focusGained
public abstract class FlightFocusLostListener implements FocusListener {

  @Override
  public void focusGained(FocusEvent e) {
  }

}