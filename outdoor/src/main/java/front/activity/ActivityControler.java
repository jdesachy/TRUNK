package front.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import front.activity.db.ActivityDBDelegate;
import front.activity.db.exception.DeleteActivityException;

@ManagedBean(name = "activityControler")
@SessionScoped
public class ActivityControler implements Serializable {

	private static final long serialVersionUID = -6035201373818263225L;

	private final Logger log = Logger.getLogger(ActivityControler.class
			.getName());

	private final ActivityDBDelegate activityLoader = new ActivityDBDelegate();

	private ActivityBean actionBean;

	private List<ActivityBean> activitiesBean = new ArrayList<ActivityBean>();

	public String loadList() {
		activitiesBean = activityLoader.loadAllActivity();
		return "list";
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

}
