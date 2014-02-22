package db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import front.profile.db.Person;
import Connection.HibernateUtil;

public class DBQueryExecutor implements Serializable {

	private static final long serialVersionUID = 3424179823137953112L;

	public List<DBObject> executeQuery(String sqlParameters) {
		Session session = getSession();
		session.beginTransaction();
		List<DBObject> list = session.createQuery(sqlParameters).list();
		session.getTransaction().commit();
		return list;
	}

	public void delete(Bean beanToDelete) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(beanToDelete.toDBObject());
		session.getTransaction().commit();
	}

	public void insert(DBObject object) {
		Session session = getSession();
		session.beginTransaction();
		session.save(object);
		session.getTransaction().commit();
	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}