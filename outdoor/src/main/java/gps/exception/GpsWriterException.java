package gps.exception;

public class GpsWriterException extends Exception {

	public GpsWriterException(Exception e) {
		super(
				"Une erreur de traitement est survenue lors de l'ecriture du nouveau fichier Gps",
				e);
	}

	private static final long serialVersionUID = -2581241883458610058L;

}
