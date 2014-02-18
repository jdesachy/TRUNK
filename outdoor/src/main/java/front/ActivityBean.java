package front;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import db.Activity;

@ManagedBean(name = "activityBean")
@ViewScoped
public class ActivityBean implements Serializable {

	private static final long serialVersionUID = 4437794882806300607L;

	private long id;
	private String name;
	private Date date;
	private String massif;
	private int denivele;
	private String comment;
	private String type;

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

	public static ActivityBean fromDBObject(Activity activity) {
		ActivityBean activityBean = new ActivityBean();
		activityBean.setId(activity.getId());
		activityBean.setName(activity.getName());
		activityBean.setDate(activity.getDate());
		activityBean.setMassif(activity.getMassif());
		activityBean.setDenivele(activity.getDenivele());
		activityBean.setComment(activity.getComment());
		activityBean.setType(activity.getType());
		return activityBean;
	}

	public Activity toDBObject() {
		Activity activity = new Activity();
		activity.setId(id);
		activity.setName(name);
		activity.setMassif(massif);
		activity.setDate(date);
		activity.setDenivele(denivele);
		activity.setComment(comment);
		activity.setType(type);
		return activity;
	}
}
