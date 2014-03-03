package front.activity.db.exception;

import db.exception.InsertDataException;
import front.activity.ActivityBean;

public class ActivityInsertException extends Exception {

	public ActivityInsertException(ActivityBean bean, InsertDataException e) {
		super("Erreur à la création de l'activité : " + bean, e);
	}

	private static final long serialVersionUID = 2355874723082045824L;

}
