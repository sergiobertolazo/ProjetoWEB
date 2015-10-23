 
package  util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

 
@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {

  private final int limit;
  private boolean toUppercase = false;

  public JTextFieldLimit(int limit) {
    this.limit = limit;
  }

  public JTextFieldLimit(int limit, boolean upper) {
    this.limit = limit;
    toUppercase = upper;
  }

  @Override
  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
    if ((getLength() + str.length()) <= limit) {
      if (toUppercase) {
        str = str.toUpperCase();
      }
      super.insertString(offset, str, attr);
    }
  }

}