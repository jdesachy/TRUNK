package front.stats;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import front.activity.ActivityBean;

public class StatisticBuilder implements Serializable {

	private static final long serialVersionUID = 7035819998641155616L;
	private final List<ActivityBean> statisticsBean;

	public StatisticBuilder(List<ActivityBean> statisticsBean) {
		this.statisticsBean = statisticsBean;
	}

	public int getTotalTraveled(Integer year) {
		int result = 0;
		Calendar minDate = getMinDate(year);
		Calendar maxDate = getMaxDate(year);

		for (ActivityBean bean : statisticsBean) {
			if (isProcessing(minDate, maxDate, bean)) {
				result += bean.getDenivele();
			}
		}

		return result;
	}

	private Calendar getMaxDate(Integer year) {
		Calendar maxDate = Calendar.getInstance();
		maxDate.set(Calendar.YEAR, year);
		maxDate.set(Calendar.MONTH, Calendar.AUGUST);
		maxDate.set(Calendar.DAY_OF_MONTH, 1);
		return maxDate;
	}

	private Calendar getMinDate(Integer year) {
		Calendar minDate = Calendar.getInstance();
		minDate.set(Calendar.YEAR, year - 1);
		minDate.set(Calendar.MONTH, Calendar.AUGUST);
		minDate.set(Calendar.DAY_OF_MONTH, 1);
		return minDate;
	}

	private boolean isProcessing(Calendar minDate, Calendar maxDate,
			ActivityBean bean) {
		return bean.getDate().compareTo(minDate.getTime()) > 0
				&& bean.getDate().compareTo(maxDate.getTime()) < 0;
	}

	public int getTotalOut(Integer curYear) {
		int result = 0;
		Calendar minDate = getMinDate(curYear);
		Calendar maxDate = getMaxDate(curYear);
		for (ActivityBean bean : statisticsBean) {
			if (isProcessing(minDate, maxDate, bean)) {
				result++;
			}
		}
		return result;
	}

}
