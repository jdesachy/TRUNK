package front;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import db.ActivityLoader;
import front.ski.FormSki;

@ManagedBean(eager = true)
@ViewScoped
public class ActivityControler implements Serializable {

	private static final long serialVersionUID = -6035201373818263225L;

	private final ActivityLoader activityLoader = new ActivityLoader();
	private List<ActivityBean> activitiesBean = new ArrayList<ActivityBean>();
	private FormSki formSki = new FormSki();
	private ActivityBean actionBean;

	public void init(ComponentSystemEvent event) {
		activitiesBean = activityLoader.loadAllActivity();
	}

	public String delete() {
		activitiesBean.remove(actionBean);
		activityLoader.delete(actionBean);
		return null;
	}

	public List<ActivityBean> getActivitiesBean() {
		return activitiesBean;
	}

	public ActivityBean getActionBean() {
		return actionBean;
	}

	public void setActionBean(ActivityBean actionBean) {
		this.actionBean = actionBean;
	}

	public int getLength() {
		return activitiesBean.size();
	}

	public FormSki getFormSki() {
		return formSki;
	}

	public String update() {
		formSki.setActionBean(actionBean);
		return null;
	}

	public void setFormSki(FormSki formSki) {
		this.formSki = formSki;
	}

}
