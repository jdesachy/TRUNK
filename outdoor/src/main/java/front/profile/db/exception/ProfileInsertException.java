package front.profile.db.exception;

import db.exception.InsertDataException;
import front.profile.db.Person;

public class ProfileInsertException extends Exception {

	public ProfileInsertException(Person person, InsertDataException e) {
		super("Impossible de créer le profile suivant : " + person, e);
	}

	private static final long serialVersionUID = 7378554325388523875L;

}
