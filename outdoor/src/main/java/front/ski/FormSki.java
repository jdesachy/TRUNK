package front.ski;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import front.activity.ActivityBean;
import front.activity.db.ActivityDBDelegate;
import front.activity.db.ActivityType;
import front.activity.db.exception.ActivityInsertException;
import front.activity.db.exception.ActivityUpdateException;
import front.profile.PersonBean;
import front.profile.db.ProfileDBDelegate;
import front.profile.db.exception.ProfileLoaderException;

@ManagedBean(name = "formSki")
@SessionScoped
public class FormSki implements Serializable {

	private final Logger log = Logger.getLogger(FormSki.class.getName());
	private static final long serialVersionUID = -7268492525793192133L;
	private ActivityBean actionBean;

	private String name;
	private String comment;
	private Date date;
	private List<String> massifs;
	private String massif;
	private int traveledAltitude;
	private long id;
	private List<PersonBean> selectedPersons;
	private List<PersonBean> listPersons;
	private List<String> listPictures = new ArrayList<String>();
	private String newPitcure;

	private boolean edit;
	private final ProfileDBDelegate profileDBDelegate = new ProfileDBDelegate();
	private final ActivityDBDelegate activityDBDelegate = new ActivityDBDelegate();

	public String editActivity() {
		try {
			listPersons = profileDBDelegate.loadProfiles();
		} catch (ProfileLoaderException e) {
			log.log(Level.SEVERE, e.getMessage());
			return "erreur";
		}
		return "edit";
	}

	public String validate() throws UnknownHostException, IOException {
		try {
			activityDBDelegate.createActivity(createBean());
		} catch (ActivityInsertException e) {
			log.log(Level.SEVERE, e.getMessage());
			return "erreur";
		}
		cleanForm();
		return "success";
	}

	private ActivityBean createBean() {
		ActivityBean bean = new ActivityBean();
		bean.setName(name);
		bean.setDate(date);
		bean.setDenivele(traveledAltitude);
		bean.setMassif(massif);
		bean.setComment(comment);
		bean.setType(ActivityType.SKI.name());
		bean.setPersons(selectedPersons);
		bean.setPictures(listPictures);
		return bean;
	}

	public String save() {
		ActivityBean bean = createBean();
		bean.setId(id);
		try {
			activityDBDelegate.update(bean);
		} catch (ActivityUpdateException e) {
			log.log(Level.SEVERE, e.getMessage());
			return "erreur";
		}
		cleanForm();
		return "success";
	}

	public void addPicture(ActionEvent event) {
		listPictures.add(newPitcure);
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
		id = actionBean.getId();
		name = actionBean.getName();
		date = actionBean.getDate();
		traveledAltitude = actionBean.getDenivele();
		comment = actionBean.getComment();
		massif = actionBean.getMassif();
		selectedPersons = actionBean.getPersons();
		return "edit";
	}

	private void cleanForm() {
		this.setEdit(false);
		name = null;
		date = null;
		traveledAltitude = 0;
		comment = null;
		massif = null;
		selectedPersons = null;
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

	public List<PersonBean> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<PersonBean> selectedPersons) {
		this.selectedPersons = selectedPersons;
	}

	public List<PersonBean> getListPersons() {
		return listPersons;
	}

	public void setListPersons(List<PersonBean> listPersons) {
		this.listPersons = listPersons;
	}

	public List<String> getListPictures() {
		return listPictures;
	}

	public void setListPictures(List<String> listPictures) {
		this.listPictures = listPictures;
	}

	public String getNewPitcure() {
		return newPitcure;
	}

	public void setNewPitcure(String newPitcure) {
		this.newPitcure = newPitcure;
	}

}
