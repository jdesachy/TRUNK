package db.exception;

public class DeleteDataException extends Exception {

	public DeleteDataException(Exception e) {
		super("Imposible de supprimer la donn�e.", e);
	}

	private static final long serialVersionUID = 2763296925453537078L;

}
