package listener.exception;


public class InitConfigurationException extends Exception {

	public InitConfigurationException(Exception e) {
		super("Erreur ˆ l'initalisation de la configuration", e);
	}

	private static final long serialVersionUID = -3650599341319705723L;

}
