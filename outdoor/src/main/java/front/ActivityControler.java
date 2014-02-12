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
	private final FormStats formStats = new FormStats();
	private ActivityBean actionBean;

	private Integer selectedTab;

	public void init(ComponentSystemEvent event) {
		if (activitiesBean.isEmpty()) {
			activitiesBean = activityLoader.loadAllActivity();
			formStats.setStatisticsBean(activitiesBean);
			formStats.createTotalChart();
		}
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

	public void setFormSki(FormSki formSki) {
		this.formSki = formSki;
	}

	public Integer getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(Integer selectedTab) {
		this.selectedTab = selectedTab;
	}

	public String update() {
		formSki.setActionBean(actionBean);
		selectedTab = 1;
		return null;
	}

	public FormStats getFormStats() {
		return formStats;
	}
}
