 
package model;

import java.util.ResourceBundle;
 
public enum Perfil {

  ATENDENTE("Atendente", "Attendant", "Asistente"),
  SUPERVISOR("Supervisor", "Supervisor", "Supervisor");

  private String br;
  private String us;
  private String es;

  private Perfil(String br, String us, String es) {
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

  public static Perfil fromString(String nome) {
    if (nome != null) {
      for (Perfil perfil : Perfil.values()) {
        if (nome.equals(perfil.br) || nome.equals(perfil.us) || nome.equals(perfil.es)) {
          return perfil;
        }
      }
    }
    return null;
  }

}