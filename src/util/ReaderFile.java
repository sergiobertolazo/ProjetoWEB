 
package  util;

import java.util.List;
 
public interface ReaderFile<T> {

  void openFile();

  List<T> readFile();

  void closeFile();

}