package front.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import db.Bean;
import front.activity.db.Activity;
import front.profile.PersonBean;
import front.profile.db.Person;

@ManagedBean(name = "activityBean")
@ViewScoped
public class ActivityBean implements Serializable, Bean {

	private static final long serialVersionUID = 4437794882806300607L;

	private long id;
	private String name;
	private Date date;
	private String massif;
	private int denivele;
	private String comment;
	private String type;
	private List<PersonBean> persons;
	private List<String> pictures;

	public ActivityBean(Activity activity) {
		id = activity.getId();
		name = activity.getName();
		date = activity.getDate();
		massif = activity.getMassif();
		denivele = activity.getDenivele();
		comment = activity.getComment();
		type = activity.getType();
		persons = convertList(activity.getPersons());
		pictures = new ArrayList<String>(activity.getPictures());
	}

	public ActivityBean() {
	}

	private List<PersonBean> convertList(Set<Person> personsToConvert) {
		ArrayList<PersonBean> res = new ArrayList<PersonBean>();
		for (Person person : personsToConvert) {
			res.add(new PersonBean(person));
		}
		return res;
	}

	@Override
	public Activity toDBObject() {
		Activity activity = new Activity();
		activity.setId(id);
		activity.setName(name);
		activity.setMassif(massif);
		activity.setDate(date);
		activity.setDenivele(denivele);
		activity.setComment(comment);
		activity.setType(type);
		activity.setPersons(convertSet());
		activity.setPictures(getPictures());
		return activity;
	}

	private Set<String> getPictures() {
		Set<String> set = null;
		if (pictures != null) {
			set = new HashSet<String>(pictures);
		} else {
			set = new HashSet<String>();
		}
		return set;
	}

	private Set<Person> convertSet() {
		HashSet<Person> hashSet = new HashSet<Person>();
		for (PersonBean person : persons) {
			hashSet.add(person.toDBObject());
		}
		return hashSet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMassif() {
		return massif;
	}

	public void setMassif(String massif) {
		this.massif = massif;
	}

	public int getDenivele() {
		return denivele;
	}

	public void setDenivele(int denivele) {
		this.denivele = denivele;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<PersonBean> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonBean> persons) {
		this.persons = persons;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
}
