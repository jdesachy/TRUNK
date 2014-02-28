package front.profile.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import db.DBObject;
import db.DBQueryExecutor;
import db.exception.DeleteDataException;
import db.exception.ExecutionQueryException;
import db.exception.InsertDataException;
import front.profile.PersonBean;
import front.profile.db.exception.DeleteProfileException;
import front.profile.db.exception.ProfileInsertException;
import front.profile.db.exception.ProfileLoaderException;

public class ProfileDBDelegate implements Serializable {

	private static final long serialVersionUID = 7211026033988435976L;
	private final DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();

	public void create(Person person) throws ProfileInsertException {
		try {
			dbQueryExecutor.insert(person);
		} catch (InsertDataException e) {
			throw new ProfileInsertException(person, e);
		}
	}

	public List<PersonBean> loadProfiles() throws ProfileLoaderException {
		List<DBObject> result = null;
		try {
			result = dbQueryExecutor.executeQuery("from Person");
		} catch (ExecutionQueryException e) {
			throw new ProfileLoaderException(e);
		}
		return convertFromDBObject(result);
	}

	private List<PersonBean> convertFromDBObject(List<DBObject> result) {
		List<PersonBean> newList = new ArrayList<PersonBean>();
		for (DBObject dbObject : result) {
			newList.add(new PersonBean((Person) dbObject));
		}
		return newList;
	}

	public void delete(PersonBean person) throws DeleteProfileException {
		try {
			dbQueryExecutor.delete(person);
		} catch (DeleteDataException e) {
			throw new DeleteProfileException(person, e);
		}
	}

}
