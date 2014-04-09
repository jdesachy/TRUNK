package front.activity;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import front.activity.db.ActivityDBDelegate;
import front.activity.db.exception.ActivityLoaderException;
import front.activity.db.exception.DeleteActivityException;

@ManagedBean(name = "activityControler")
@SessionScoped
public class ActivityControler implements Serializable {

	private static final long serialVersionUID = -6035201373818263225L;

	private final Logger log = Logger.getLogger(ActivityControler.class
			.getName());

	private final ActivityDBDelegate activityLoader = new ActivityDBDelegate();

	private ActivityBean actionBean;
	private ActivityBean selectedBean;

	private List<ActivityBean> activitiesBean;
	private List<ActivityBean> filteredBean;

	public String loadList() {
		try {
			activitiesBean = activityLoader.loadAllActivity();
		} catch (ActivityLoaderException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			return "erreur";
		}
		return "/allActivities.xhtml";
	}

	public String delete() {
		try {
			activityLoader.delete(actionBean);
			activitiesBean.remove(actionBean);
		} catch (DeleteActivityException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		return null;
	}

	public ActivityBean getActionBean() {
		return actionBean;
	}

	public void setActionBean(ActivityBean actionBean) {
		this.actionBean = actionBean;
	}

	public List<ActivityBean> getActivitiesBean() {
		return activitiesBean;
	}

	public void setActivitiesBean(List<ActivityBean> activitiesBean) {
		this.activitiesBean = activitiesBean;
	}

	public ActivityBean getSelectedBean() {
		return selectedBean;
	}

	public void setSelectedBean(ActivityBean selectedBean) {
		this.selectedBean = selectedBean;
	}

	public List<ActivityBean> getFilteredBean() {
		return filteredBean;
	}

	public void setFilteredBean(List<ActivityBean> filteredBean) {
		this.filteredBean = filteredBean;
	}

}
