package gps.exception;

public class GpsUploadException extends Exception {

	public GpsUploadException(Exception e) {
		super("Une erreur s'est produite lors du transfère de fichier", e);
	}

	public GpsUploadException() {
		super("Impossible de se connecter au serveur");
	}

	private static final long serialVersionUID = 4759338559578016108L;

}
