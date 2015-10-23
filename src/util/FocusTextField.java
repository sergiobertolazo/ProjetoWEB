 
package  util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
 
public class FocusTextField implements FocusListener {

  private final Font antes = new Font("Century Gothic", Font.ITALIC, 13);
  private final Font depois = new Font("Arial", Font.PLAIN, 13);

  private JTextField[] fields;
  private String[] strings;

  public void setField(JTextField... fields) {
    this.fields = fields;
  }

  public void setText(String... strings) {
    this.strings = strings;
  }

  @Override
  public void focusGained(FocusEvent e) {
    for (int i = 0; i < fields.length; i++) {
      if (e.getSource() == fields[i] && fields[i].getText().equals(strings[i])) {
        fields[i].setText("");
        fields[i].setFont(depois);
        fields[i].setForeground(Color.BLACK);
      }
    }
  }

  @Override
  public void focusLost(FocusEvent e) {
    for (int i = 0; i < fields.length; i++) {
      if (e.getSource() == fields[i] && fields[i].getText().equals("")) {
        fields[i].setText(strings[i]);
        fields[i].setFont(antes);
        fields[i].setForeground(Color.GRAY);
      }

    }
  }

}