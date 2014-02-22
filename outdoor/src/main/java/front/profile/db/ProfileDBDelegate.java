package front.profile.db;

import java.util.ArrayList;
import java.util.List;

import db.DBObject;
import db.DBQueryExecutor;
import front.profile.PersonBean;

public class ProfileDBDelegate {

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

	public void delete(PersonBean personBean) {
		dbQueryExecutor.delete(personBean);
	}
}
