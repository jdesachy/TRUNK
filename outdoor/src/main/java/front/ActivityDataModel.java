package front;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import front.activity.ActivityBean;

public class ActivityDataModel extends ListDataModel<ActivityBean> implements
		SelectableDataModel<ActivityBean> {

	public ActivityDataModel() {
		super();
	}

	public ActivityDataModel(List<ActivityBean> list) {
		super(list);
	}

	@Override
	public ActivityBean getRowData(String arg0) {
		List<ActivityBean> data = (List<ActivityBean>) getWrappedData();
		for (ActivityBean activityBean : data) {
			if (String.valueOf(activityBean.getId()).equals(arg0)) {
				return activityBean;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(ActivityBean arg0) {
		return String.valueOf(arg0.getId());
	}

}
