package db.exception;

public class ExecutionQueryException extends Exception {

	public ExecutionQueryException(Exception e) {
		super("Impossible d'executer la requête " + e.getMessage());
	}

	private static final long serialVersionUID = -1759989491236660305L;

}
