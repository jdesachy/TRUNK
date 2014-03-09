package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import db.DBQueryExecutor;

public class InitApplication implements ServletContextListener {

	private final Logger log = Logger.getLogger(InitApplication.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// Init configuration
			Configuration.getInstance();

			// Init first jdbc connection
			DBQueryExecutor executor = new DBQueryExecutor();
			executor.initConnection();
		} catch (Exception e) {
			log.log(Level.FATAL,
					"Une erreur est survenue au démarrage de l'application", e);
		}
	}

}
