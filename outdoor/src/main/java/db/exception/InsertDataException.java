package db.exception;

public class InsertDataException extends Exception {

	private static final long serialVersionUID = 2855199557627345686L;

	public InsertDataException(Exception e) {
		super("Impossible d'ins�rer les donn�es : " + e.getMessage());
	}
}
