package front.activity.db.exception;

import db.exception.DeleteDataException;
import front.activity.ActivityBean;

public class DeleteActivityException extends Exception {

	public DeleteActivityException(ActivityBean beanToDelete,
			DeleteDataException e) {
		super("L'acitivité " + beanToDelete.toString()
				+ "ne peut pas être supprimée", e);
	}

	private static final long serialVersionUID = -6807311234991393262L;

}
