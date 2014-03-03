package front.activity.db.exception;

import db.exception.UpdateDataException;
import front.activity.ActivityBean;

public class ActivityUpdateException extends Exception {

	private static final long serialVersionUID = -6216835228634088901L;

	public ActivityUpdateException(ActivityBean beanToUpdate,
			UpdateDataException e) {
		super("Impossible de mettre � jour l'activit� : " + beanToUpdate, e);
	}

}
