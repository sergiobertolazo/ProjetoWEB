package to;

import java.io.Serializable;

public class VooTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int id, idAeronave;
	public String codigo, destino, origem, data, hora, situacao;
	public double valor;
}
