package front.stats;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;

import front.activity.ActivityBean;
import front.activity.db.ActivityDBDelegate;

@ManagedBean(name = "statisticController")
@SessionScoped
public class StatisticController implements Serializable {

	private static final long serialVersionUID = 7725937619205823317L;

	private final ActivityDBDelegate activityLoader = new ActivityDBDelegate();
	private final ChartBuilder chartBuilder = new ChartBuilder();

	private CartesianChartModel linearModel;
	private int maxTotal;

	public String loadStatistics() {
		List<ActivityBean> activitiesBean = activityLoader.loadAllActivity();
		linearModel = chartBuilder.getTotalChart(activitiesBean);
		maxTotal = chartBuilder.getMax(linearModel);
		return "stats";
	}

	public ChartModel getLinearModel() {
		return linearModel;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

}
