 
package model;

import java.util.ResourceBundle;

 
public enum Status {

  DISPONIVEL("Disponível", "Available", "Disponible"),
  INDISPONIVEL("Indisponível", "Unavailable", "Indisponible"),
  CONFIRMADO("Confirmado", "Confirmed", "Confirmado"),
  CANCELADO("Cancelado", "Canceled", "Cancelado"),
  ATRASADO("Atrasado", "Late", "Retrasado"),
  EMBARQUE("Embarque", "Boarding", "Embarque"),
  FINALIZADO("Finalizado", "Finalized", "Finalizado");

  private String br;
  private String us;
  private String es;

  private Status(String br, String us, String es) {
    this.br = br;
    this.us = us;
    this.es = es;
  }

  public String setBundle(ResourceBundle bundle) {
    String country = bundle.getString("country");
    String res = null;
    if (country.equals("ES")) {
      res = es;
    }
    else if (country.equals("US")) {
      res = us;
    }
    else {
      res = br;
    }
    return res;
  }

  public String setBundle(String bundle, String web) {
    String country = bundle;
    String res = null;
    if (country.equalsIgnoreCase("ES")) {
      res = es;
    }
    else if (country.equalsIgnoreCase("US")) {
      res = us;
    }
    else {
      res = br;
    }
    return res;
  }

  public String setName(String lang) {
    String res = null;
    if (lang.toUpperCase().equals("ES"))
      res = es;
    else if (lang.toUpperCase().equals("EN"))
      res = us;
    else
      res = br;
    return res;
  }

  public static Status fromString(String nome) {
    if (nome != null) {
      for (Status status : Status.values()) {
        if (nome.equals(status.br) || nome.equals(status.us) || nome.equals(status.es)) {
          return status;
        }
      }
    }
    return null;
  }

}