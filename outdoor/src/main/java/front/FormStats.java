package front;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;

import front.stats.ChartBuilder;

public class FormStats implements Serializable {

	private static final long serialVersionUID = -1499667296375608604L;

	private List<ActivityBean> statisticsBean = new ArrayList<ActivityBean>();
	private final ChartBuilder chartBuilder = new ChartBuilder();
	private int maxTotal;

	private CartesianChartModel linearModel;

	public void createTotalChart() {
		linearModel = chartBuilder.getTotalChart(statisticsBean);
		maxTotal = chartBuilder.getMax(linearModel);
	}

	public List<ActivityBean> getStatisticsBean() {
		return statisticsBean;
	}

	public void setStatisticsBean(List<ActivityBean> statisticsBean) {
		this.statisticsBean = statisticsBean;
	}

	public CartesianChartModel getLinearModel() {
		return linearModel;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

}
