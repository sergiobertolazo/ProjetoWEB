 
package model;

import java.util.ResourceBundle;

 
public enum FormaDeTratamento {

  SR("Sr.", "Mr.", "Sr."),
  SRA("Sra.", "Mrs.", "Sra."),
  SRTA("Srta.", "Miss", "Miss.");

  private String br;
  private String us;
  private String es;

  private FormaDeTratamento(String br, String us, String es) {
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

  public static FormaDeTratamento fromString(String nome) {
    if (nome != null) {
      for (FormaDeTratamento tratamento : FormaDeTratamento.values()) {
        if (nome.equals(tratamento.br) || nome.equals(tratamento.us) || nome.equals(tratamento.es)) {
          return tratamento;
        }
      }
    }
    return null;
  }

}