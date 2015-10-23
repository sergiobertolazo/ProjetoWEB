
package  util;


@SuppressWarnings("serial")
public class CPFInvalidException extends RuntimeException {

  private final String digito;

  public CPFInvalidException(String digito) {
    this.digito = digito;
  }
  @Override
  public String getMessage() {
    return String.format("O CPF %s é invalido", digito);
  }

}