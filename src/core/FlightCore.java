 
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbc.Database;
import model.Usuario;

 
public class FlightCore implements ServletContextListener {

  private static FlightCore core = new FlightCore();
  private Logger log;
  private Usuario usuarioLogado;
  private Database jdbc;

  public static FlightCore getInstance() {
    return core != null ? core : new FlightCore(); // Singleton
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    core = this;
    initLog();
    initDatabase();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }

  private void initLog() {
    log = LoggerFactory.getLogger(FlightCore.class);
    File folder = new File(System.getProperty("user.home") + File.separator + "Log");
    // Cria uma pasta no computador caso nao exista para salvar os LOGs diários
    // do sistema
    if (!folder.exists()) {
      folder.mkdir();
      folder.setWritable(true, true);
      log.info("Log Folder created");
    }

    log.info("Starting Log Flight Web");
  }
  
  private void initDatabase() {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/jdbc.properties")));

      Properties properties = new Properties();
      properties.load(reader);

      jdbc = Database.valueOf(properties.getProperty("jdbc").toUpperCase());
      
      log.info("Database loaded [" + jdbc + "]");
    } catch (Exception e) {
      core.logError(e.getMessage(), e);
    }
  }

  public void logError(String msg, Exception e) {
    log.error(msg, e);
  }

  public void logInfo(String msg) {
    log.info(msg);
  }

  public Usuario getUsuarioLogado() {
    return usuarioLogado;
  }

  public void setUsuarioLogado(Usuario usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
  }

  public Database getJdbc() {
    return jdbc;
  }

}