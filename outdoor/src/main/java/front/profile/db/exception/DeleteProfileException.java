package front.profile.db.exception;

import db.exception.DeleteDataException;
import front.profile.PersonBean;

public class DeleteProfileException extends Exception {

	public DeleteProfileException(PersonBean person, DeleteDataException e) {
		super("Le profile " + person.toString() + " ne peut pas �tre supprim�",
				e);
	}

	private static final long serialVersionUID = 7600537654525830754L;

}
