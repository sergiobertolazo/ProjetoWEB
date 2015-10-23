package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnFactory {
	private static int banco;


	public static int getBanco() {

		return banco;
	}

	public static void setBanco(int b) {
		
			banco = b;
	}

	public static Connection conectar() throws SQLException {

			return ConnFactoryMySQL.conectar();
	
	}
}


