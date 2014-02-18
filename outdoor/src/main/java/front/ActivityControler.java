package front;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.ActivityLoader;

@ManagedBean(name = "activityControler")
@SessionScoped
public class ActivityControler implements Serializable {

	private static final long serialVersionUID = -6035201373818263225L;

	private final ActivityLoader activityLoader = new ActivityLoader();

	private ActivityBean actionBean;

	private List<ActivityBean> activitiesBean = new ArrayList<ActivityBean>();

	public String loadList() {
		activitiesBean = activityLoader.loadAllActivity();
		return "list";
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

}
