package front.ski;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import org.hibernate.Session;

import Connection.HibernateUtil;
import front.activity.ActivityBean;
import front.activity.db.Activity;
import front.activity.db.ActivityDBDelegate;
import front.activity.db.ActivityType;
import front.profile.PersonBean;
import front.profile.db.Person;
import front.profile.db.ProfileDBDelegate;

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
	private Set<PersonBean> selectedPersons;
	private List<PersonBean> listPersons;

	private boolean edit;
	private final ProfileDBDelegate profileDBDelegate = new ProfileDBDelegate();
	private final ActivityDBDelegate activityDBDelegate = new ActivityDBDelegate();

	public void initProfiles(ComponentSystemEvent componentSystemEvent) {
		listPersons = profileDBDelegate.loadProfiles();
	}

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
		activity.setPersons(convertBean(selectedPersons));
		activity.setType(ActivityType.SKI.name());
		return activity;
	}

	private Set<Person> convertBean(Set<PersonBean> selectedPersons2) {
		Set<Person> res = new HashSet<Person>();
		for (PersonBean personBean : selectedPersons2) {
			res.add(personBean.toDBObject());
		}
		return res;
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

	public Set<PersonBean> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(Set<PersonBean> selectedPersons) {
		this.selectedPersons = selectedPersons;
	}

	public List<PersonBean> getListPersons() {
		return listPersons;
	}

	public void setListPersons(List<PersonBean> listPersons) {
		this.listPersons = listPersons;
	}

}
