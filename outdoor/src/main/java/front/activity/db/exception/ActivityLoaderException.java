package front.activity.db.exception;

import db.exception.ExecutionQueryException;

public class ActivityLoaderException extends Exception {

	public ActivityLoaderException(ExecutionQueryException e) {
		super("Erreur au chargement des activités", e);
	}

	private static final long serialVersionUID = 8767988304911350067L;

}
