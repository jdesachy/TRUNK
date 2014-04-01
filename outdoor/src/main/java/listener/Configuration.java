package listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import listener.exception.InitConfigurationException;

public class Configuration {

	private static Configuration configuration;

	private final Logger log = Logger.getLogger(Configuration.class.getName());
	private Properties props;

	private Configuration() {
	}

	public static Configuration getInstance() throws InitConfigurationException {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.loadConfiguration();
		}
		return configuration;
	}

	private void loadConfiguration() throws InitConfigurationException {
		log.log(Level.INFO, "DÈémarrage du chargement des propriétés");
		InputStream input = null;
		try {
			input = this.getClass().getResourceAsStream("/outdoor.properties");
			props = new Properties();
			props.load(input);

			log.log(Level.INFO, "PropriÈétÈés chargéÈes avec succËès.");
		} catch (FileNotFoundException e) {
			throw new InitConfigurationException(e);
		} catch (IOException e) {
			throw new InitConfigurationException(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new InitConfigurationException(e);
				}
			}
		}
	}

	public String getString(String key) {
		return props.getProperty(key);
	}
}
