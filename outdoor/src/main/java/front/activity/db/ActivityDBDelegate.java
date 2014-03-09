package front.activity.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import db.DBObject;
import db.DBQueryExecutor;
import db.exception.DeleteDataException;
import db.exception.ExecutionQueryException;
import db.exception.InsertDataException;
import db.exception.UpdateDataException;
import front.activity.ActivityBean;
import front.activity.db.exception.ActivityInsertException;
import front.activity.db.exception.ActivityLoaderException;
import front.activity.db.exception.ActivityUpdateException;
import front.activity.db.exception.DeleteActivityException;

public class ActivityDBDelegate implements Serializable {

	private static final long serialVersionUID = 4636327288530128841L;
	private final DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();

	public void createActivity(ActivityBean bean)
			throws ActivityInsertException {
		try {
			dbQueryExecutor.insert(bean.toDBObject());
		} catch (InsertDataException e) {
			throw new ActivityInsertException(bean, e);

		}
	}

	public List<ActivityBean> loadAllActivity() throws ActivityLoaderException {
		List<DBObject> result = null;
		try {
			result = dbQueryExecutor
					.executeQuery("select distinct a from Activity a left join fetch a.persons left join fetch a.pictures");
		} catch (ExecutionQueryException e) {
			throw new ActivityLoaderException(e);
		}
		return convertToBusinnesObject(result);
	}

	private List<ActivityBean> convertToBusinnesObject(List<DBObject> result) {
		List<ActivityBean> res = new ArrayList<ActivityBean>();
		for (DBObject activity : result) {
			res.add(new ActivityBean((Activity) activity));
		}
		return res;
	}

	public void update(ActivityBean beanToUpdate)
			throws ActivityUpdateException {
		try {
			dbQueryExecutor.update(beanToUpdate.toDBObject());
		} catch (UpdateDataException e) {
			throw new ActivityUpdateException(beanToUpdate, e);
		}
	}

	public void delete(ActivityBean beanToDelete)
			throws DeleteActivityException {
		try {
			dbQueryExecutor.delete(beanToDelete);
		} catch (DeleteDataException e) {
			throw new DeleteActivityException(beanToDelete, e);
		}
	}
}
