package front.activity.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Connection.HibernateUtil;
import db.DBObject;
import db.DBQueryExecutor;
import db.exception.DeleteDataException;
import front.activity.ActivityBean;
import front.activity.db.exception.DeleteActivityException;

public class ActivityDBDelegate implements Serializable {

	private static final long serialVersionUID = 4636327288530128841L;
	private final DBQueryExecutor dbQueryExecutor = new DBQueryExecutor();

	public void createActivity(ActivityBean bean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bean.toDBObject());
		session.getTransaction().commit();
	}

	public List<ActivityBean> loadAllActivity() {
		List<DBObject> result = dbQueryExecutor
				.executeQuery("select distinct a from Activity a left join fetch a.persons");
		return convertToBusinnesObject(result);
	}

	private List<ActivityBean> convertToBusinnesObject(List<DBObject> result) {
		List<ActivityBean> res = new ArrayList<ActivityBean>();
		for (DBObject activity : result) {
			res.add(new ActivityBean((Activity) activity));
		}
		return res;
	}

	public void update(ActivityBean beanToUpdate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(beanToUpdate.toDBObject());
		session.getTransaction().commit();
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
