package factory;

import to.AeronaveTO;
import to.AssentoTO;
import to.ListaAeronaveTO;
import to.ListaVooTO;
import to.UsuarioTO;
import to.VooTO;

public class TOFactory {
	
	public static UsuarioTO getUsuarioTO(){
		return new UsuarioTO();
	}
	
	public static AeronaveTO getAeronaveTO(){
		return new AeronaveTO();
	}
	
	public static AssentoTO getAssentoTO(){
		return new AssentoTO();
	}

	public static ListaAeronaveTO getListaAeronaveTO(){
		return new ListaAeronaveTO();
	}
	
	public static VooTO getVooTO(){
		return new VooTO();
	}
	
	public static ListaVooTO getListaVooTO(){
		return new ListaVooTO();
	}
}
