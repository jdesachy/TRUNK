package front.ski;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import Connection.HibernateUtil;
import db.Activity;
import db.ActivityType;
import front.ActivityBean;

@ManagedBean(name = "formSki")
@SessionScoped
public class FormSki implements Serializable {

	private static final long serialVersionUID = -7268492525793192133L;
	private ActivityBean actionBean;

	private String name;
	private String comment;
	private Date date;
	private List<String> massifs;
	private String massif;
	private int traveledAltitude;
	private long id;

	private boolean edit;

	public String validate() throws UnknownHostException, IOException {
		createActivity();
		return "success";
	}

	private void createActivity() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(buildActivity());
		session.getTransaction().commit();
		clean();
	}

	public String save() {
		Activity updatedActivity = buildActivity();
		updatedActivity.setId(id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(updatedActivity);
		session.getTransaction().commit();
		clean();
		return "success";
	}

	private Activity buildActivity() {
		Activity activity = new Activity();
		activity.setName(name);
		activity.setDate(date);
		activity.setDenivele(traveledAltitude);
		activity.setMassif(massif);
		activity.setComment(comment);
		activity.setType(ActivityType.SKI.name());
		return activity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public List<String> getMassifs() {
		if (massifs == null) {
			massifs = new ArrayList<String>();
			Massif[] values = Massif.values();
			for (Massif value : values) {
				massifs.add(value.getMassifName());
			}
		}
		return massifs;
	}

	public int getTraveledAltitude() {
		return traveledAltitude;
	}

	public void setTraveledAltitude(int traveledAltitude) {
		this.traveledAltitude = traveledAltitude;
	}

	public String update() {
		this.setEdit(true);
		this.id = actionBean.getId();
		this.name = actionBean.getName();
		this.date = actionBean.getDate();
		this.traveledAltitude = actionBean.getDenivele();
		this.comment = actionBean.getComment();
		this.massif = actionBean.getMassif();
		return "edit";
	}

	private void clean() {
		this.setEdit(false);
		this.name = null;
		this.date = null;
		this.traveledAltitude = 0;
		this.comment = null;
		this.massif = null;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getMassif() {
		return massif;
	}

	public void setMassif(String massif) {
		this.massif = massif;
	}

	public ActivityBean getActionBean() {
		return actionBean;
	}

	public void setActionBean(ActivityBean actionBean) {
		this.actionBean = actionBean;
	}

}
