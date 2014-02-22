package front.stats;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import front.activity.ActivityBean;

public class StatisticBuilder  implements Serializable{

	private static final long serialVersionUID = 7035819998641155616L;
	private final List<ActivityBean> statisticsBean;

	public StatisticBuilder(List<ActivityBean> statisticsBean) {
		this.statisticsBean = statisticsBean;
	}

	public int getTotalTraveled(Integer year) {
		int result = 0;
		for (ActivityBean bean : statisticsBean) {
			if (isProcessing(year, bean)) {
				result += bean.getDenivele();
			}
		}

		return result;
	}

	private boolean isProcessing(Integer year, ActivityBean bean) {
		return extractYear(bean) == year;
	}

	private int extractYear(ActivityBean bean) {
		Calendar curDate = Calendar.getInstance();
		curDate.setTime(bean.getDate());
		int curYear = curDate.get(Calendar.YEAR);
		return curYear;
	}

}
