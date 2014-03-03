package db.exception;

import db.DBObject;

public class UpdateDataException extends Exception {

	private static final long serialVersionUID = 3655620121481254501L;

	public UpdateDataException(DBObject object, Exception e) {
		super("Erreur lors de la mise à jour de : " + object, e);
	}

}
