package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import Connection.HibernateUtil;
import front.ActivityBean;

public class ActivityLoader  implements Serializable{

	private static final long serialVersionUID = 4636327288530128841L;

	public List<ActivityBean> loadAllActivity() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Activity> result = executeQuery("from Activity", session);
		List<ActivityBean> res = convertToBusinnesObject(result);
		session.getTransaction().commit();
		return res;
	}

	private List<ActivityBean> convertToBusinnesObject(List<Activity> result) {
		List<ActivityBean> res = new ArrayList<ActivityBean>();
		for (Activity activity : result) {
			res.add(ActivityBean.fromDBObject(activity));
		}
		return res;
	}

	private List<Activity> executeQuery(String sqlParameters, Session session) {
		return session.createQuery(sqlParameters).list();
	}

	public void delete(ActivityBean beanToDelete) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(beanToDelete.toDBObject());
		session.getTransaction().commit();
	}
}
