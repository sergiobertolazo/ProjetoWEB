
package util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

 
public class FlightRequestWrapper implements RequestParam {

  private final ServletRequest req;

  public FlightRequestWrapper(ServletRequest req) {
    this.req = req;
  }

  @Override
  public Double doubleParam(String param) {
    try {
      String value = req.getParameter(param);
      return Double.valueOf(value);
    } catch (NullPointerException e) {
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @Override
  public Long longParam(String param) {
    try {
      String value = req.getParameter(param);
      return Long.valueOf(value);
    } catch (NullPointerException e) {
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
  @Override
  public String stringParam(String param) {
    String val = req.getParameter(param);
    if (val != null)
      return val.isEmpty() ? null : val;
    return null;
  }

  @Override
  public <E extends Enum<E>> E enumParam(Class<E> enumClass, String param) {
    E res = null;
    String value = req.getParameter(param);
    if (value != null) {
      try {
        res = Enum.valueOf(enumClass, value);
      } catch (IllegalArgumentException e) {

      }
    }

    return res;
  }

  @Override
  public Integer intParam(String param) {
    try {
      String value = req.getParameter(param);
      if (value == null || value.isEmpty())
        value = req.getAttribute(param).toString();
      return Integer.valueOf(value);
    } catch (NullPointerException e) {
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
  @Override
  public DateTime dateTimeParam(String param) {
    try {
      String value = validDate(req.getParameter(param));
      return value.isEmpty() ? null : FormatDateTime.parseToDateTime(value);
    } catch (NullPointerException e) {
      return null;
    }
  }

  @Override
  public LocalDate localDateParam(String param) {
    try {
      String value = validDate(req.getParameter(param));
      return value.isEmpty() ? null : FormatDateTime.parseToLocalDate(value);
    } catch (NullPointerException e) {
      return null;
    }
  }

  public String validDate(String value) {
    if (value.contains("_"))
      return "";
    return value;
  }

  @Override
  public Boolean booleanParam(String param) {
    String value = req.getParameter(param);
    return Boolean.valueOf(value);
  }

  @Override
  public InputStream inputStreamParam(String param) {
    try {
      return req.getInputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String getCountry() {
    return req.getLocale().getCountry();
  }

  public void set(String key, Object object) {
    req.setAttribute(key, object);
  }

}