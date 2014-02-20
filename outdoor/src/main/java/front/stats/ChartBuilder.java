package front.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import front.ActivityBean;

public class ChartBuilder  implements Serializable{

	private static final long serialVersionUID = -341228354196134346L;

	public CartesianChartModel getTotalChart(List<ActivityBean> statisticsBean) {
		CartesianChartModel linearModel = new CartesianChartModel();

		List<Integer> years = initYears(statisticsBean);
		StatisticBuilder statsBuilder = new StatisticBuilder(statisticsBean);
		BarChartSeries series1 = new BarChartSeries();
		series1.setLabel("Series 1");
		for (Integer curYear : years) {
			int totalTraveled = statsBuilder.getTotalTraveled(curYear);
			series1.set(String.valueOf(curYear.intValue()), totalTraveled);
		}
		linearModel.addSeries(series1);
		return linearModel;
	}

	private List<Integer> initYears(List<ActivityBean> statisticsBean) {
		List<Integer> years = new ArrayList<Integer>();
		for (ActivityBean activityBean : statisticsBean) {
			int year = extractYear(activityBean);
			if (!years.contains(year)) {
				years.add(year);
			}
		}
		Collections.sort(years);
		return years;
	}

	private int extractYear(ActivityBean activityBean) {
		Calendar cal = Calendar.getInstance();
		Date date = activityBean.getDate();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public int getMax(CartesianChartModel model) {
		int max = 0;
		List<ChartSeries> series = model.getSeries();
		for (ChartSeries chartSeries : series) {
			Map<Object, Number> data = chartSeries.getData();
			Iterator<Object> iterator = data.keySet().iterator();
			while (iterator.hasNext()) {
				Number number = data.get(iterator.next());
				if (number.intValue() > max) {
					max = number.intValue();
				}
			}
		}
		return max;
	}

}
