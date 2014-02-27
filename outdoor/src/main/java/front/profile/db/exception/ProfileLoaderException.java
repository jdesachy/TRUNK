package front.profile.db.exception;

import db.exception.ExecutionQueryException;

public class ProfileLoaderException extends Exception {

	public ProfileLoaderException(ExecutionQueryException e) {
		super("Impossible de charger les profiles : ", e);
	}

	private static final long serialVersionUID = 7394708018698835505L;

}
