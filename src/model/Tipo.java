 
package model;

import java.util.ResourceBundle;

 
public enum Tipo {

  ADULTO("Adulto", "Adult", "Adulto"),
  CRIANCA("Criança", "Child", "Niño"),
  BEBE("Bebê", "Baby", "Bebé");

  private String br;
  private String us;
  private String es;

  private Tipo(String br, String us, String es) {
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

  public static Tipo fromString(String nome) {
    if (nome != null) {
      for (Tipo tipo : Tipo.values()) {
        if (nome.equals(tipo.br) || nome.equals(tipo.us) || nome.equals(tipo.es)) {
          return tipo;
        }
      }
    }
    return null;
  }

}