package front.activity.db.exception;

import db.exception.DeleteDataException;
import front.activity.ActivityBean;

public class DeleteActivityException extends Exception {

	public DeleteActivityException(ActivityBean beanToDelete,
			DeleteDataException e) {
		super("L'acitivit� " + beanToDelete.toString()
				+ "ne peut pas �tre supprim�e", e);
	}

	private static final long serialVersionUID = -6807311234991393262L;

}
