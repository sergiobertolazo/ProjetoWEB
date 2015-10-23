
package  jdbc;

import  core.FlightCore;


public class FactoryJdbc {

  private static FactoryJdbc factoryJdbc = new FactoryJdbc();
  private FlightCore core = FlightCore.getInstance();

  public static FactoryJdbc getInstance() {
    return factoryJdbc != null ? factoryJdbc : new FactoryJdbc(); // Singleton
  }

  public Database getDatabase() {
    if (core.getJdbc().equals(Database.MYSQL)) {
      return Database.MYSQL;
    } else if (core.getJdbc().equals(Database.ORACLE)) {
      return Database.ORACLE;
    }  else {
      return Database.SQL_SERVER;
    }
  }

}