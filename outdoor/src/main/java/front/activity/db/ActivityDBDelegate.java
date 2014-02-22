package front.activity.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import db.DBObject;
import db.DBQueryExecutor;
import front.activity.ActivityBean;

public class ActivityDBDelegate implements Serializable {

	private static final long serialVersionUID = 4636327288530128841L;
	private final DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();

	public List<ActivityBean> loadAllActivity() {
		List<DBObject> result = dbQueryExecutor.executeQuery("from Activity");
		return convertToBusinnesObject(result);
	}

	private List<ActivityBean> convertToBusinnesObject(List<DBObject> result) {
		List<ActivityBean> res = new ArrayList<ActivityBean>();
		for (DBObject activity : result) {
			res.add(new ActivityBean((Activity) activity));
		}
		return res;
	}

	public void delete(ActivityBean beanToDelete) {
		dbQueryExecutor.delete(beanToDelete);
	}
}
