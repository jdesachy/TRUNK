package front.profile.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import db.DBObject;
import db.DBQueryExecutor;
import db.exception.DeleteDataException;
import front.profile.PersonBean;
import front.profile.db.exception.DeleteProfileException;

public class ProfileDBDelegate implements Serializable {

	private static final long serialVersionUID = 7211026033988435976L;
	private final DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();

	public void create(Person person) {
		dbQueryExecutor.insert(person);
	}

	public List<PersonBean> loadProfiles() {
		List<DBObject> result = dbQueryExecutor.executeQuery("from Person");
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
