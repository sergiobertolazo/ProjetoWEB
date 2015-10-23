 
package  util;

import java.io.InputStream;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

 
public class Param<T> {

  private final T value;

  // Utilizados para fazer um Wrapper através de um HashMap<K, V>, isto para
  // podermos pegar qualquer dado da view como um String e, ao passar para os
  // Wrappers (NomeDaClasseUpdate.java, NomeDaClasseCreate.java e Loader.java)
  // os dados venham com o devidos tipos corretos
  public Param(T value) {
    this.value = value;
  }

  public static Param<?> parseValue(Object object) {
    Param<?> val = null;

    if (object instanceof Integer) {
      val = new Param<Integer>((Integer) object);
    }

    if (object instanceof Long) {
      val = new Param<Long>((Long) object);
    }

    if (object instanceof Double) {
      val = new Param<Double>((Double) object);
    }

    if (object instanceof String) {
      val = new Param<String>((String) object);
    }

    if (object instanceof Boolean) {
      val = new Param<Boolean>((Boolean) object);
    }

    if (object instanceof LocalDate) {
      val = new Param<LocalDate>((LocalDate) object);
    }

    if (object instanceof DateTime) {
      val = new Param<DateTime>((DateTime) object);
    }

    if (object instanceof InputStream) {
      val = new Param<InputStream>((InputStream) object);
    }

    return val;
  }

  public T getValue() {
    return value;
  }

}