package db;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import db.exception.DeleteDataException;
import db.exception.ExecutionQueryException;
import db.exception.InsertDataException;
import front.profile.db.Person;
import Connection.HibernateUtil;

public class DBQueryExecutor implements Serializable {

	private static final long serialVersionUID = 3424179823137953112L;

	public List<DBObject> executeQuery(String sqlParameters)
			throws ExecutionQueryException {
		List<DBObject> list = null;
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			list = session.createQuery(sqlParameters).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			rolleback(session);
			throw new ExecutionQueryException(e);
		} finally {
			close(session);
		}
		return list;
	}

	public void delete(Bean beanToDelete) throws DeleteDataException {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.delete(beanToDelete.toDBObject());
			session.getTransaction().commit();
		} catch (Exception e) {
			rolleback(session);
			throw new DeleteDataException(e);
		} finally {
			close(session);
		}
	}

	public void insert(DBObject object) throws InsertDataException {
		Session session = null;
		try {
			session = getSession();
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			rolleback(session);
			throw new InsertDataException(e);
		} finally {
			close(session);
		}
	}

	private void rolleback(Session session) {
		if (session != null) {
			session.getTransaction().rollback();
		}
	}

	private void close(Session session) {
		if (session != null) {
			session.close();
		}
	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
