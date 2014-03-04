package front.activity;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import front.ActivityDataModel;
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

	private ActivityDataModel activitiesBean;

	public String loadList() {
		try {
			activitiesBean = new ActivityDataModel(
					activityLoader.loadAllActivity());
		} catch (ActivityLoaderException e) {
			log.log(Level.SEVERE, e.getMessage());
			return "erreur";
		}
		return "list";
	}

	public String delete() {
		try {
			activityLoader.delete(actionBean);
			// activitiesBean.remove(actionBean);
		} catch (DeleteActivityException e) {
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		return null;
	}

	public void showDetail() {
		RequestContext.getCurrentInstance().openDialog("viewActivity");
	}

	public ActivityDataModel getActivitiesBean() {
		return activitiesBean;
	}

	public ActivityBean getActionBean() {
		return actionBean;
	}

	public void setActionBean(ActivityBean actionBean) {
		this.actionBean = actionBean;
	}

	public int getLength() {
		return activitiesBean.getRowCount();
	}

}
