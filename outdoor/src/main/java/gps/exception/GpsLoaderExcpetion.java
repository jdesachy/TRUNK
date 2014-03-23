package gps.exception;


public class GpsLoaderExcpetion extends Exception {

	public GpsLoaderExcpetion(Exception e) {
		super("Impossible de lire le fichier gps", e);
	}

	private static final long serialVersionUID = 7351585585239861148L;

}
