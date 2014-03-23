package gps;

import gps.description.GpxFile;
import gps.description.Position;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import listener.Configuration;
import listener.exception.InitConfigurationException;

public class GpsConverter {

	public GpsConverter() {
	}

	public GpxFile convert(GpxFile fileToConvert)
			throws InitConfigurationException {
		GpxFile res = fileToConvert;
		long interval = readConfigurationInterval();
		List<Position> newPositions = buildNewPositions(res, interval);
		res.getTrk().setTrkseg(newPositions);
		return res;
	}

	private List<Position> buildNewPositions(GpxFile res, long interval) {
		List<Position> newPositions = new ArrayList<Position>();
		List<Position> positions = res.getTrk().getTrkseg();
		Position lastPos = positions.get(0);
		newPositions.add(lastPos);
		for (int index = 1; index < positions.size(); index++) {
			Position curPos = positions.get(index);
			Date curTime = curPos.getTime();
			Date lastTime = lastPos.getTime();
			if (lengthBeetween(curTime, lastTime) > interval) {
				newPositions.add(curPos);
				lastPos = curPos;
			}
		}
		return newPositions;
	}

	private long readConfigurationInterval() throws InitConfigurationException {
		String precisionString = Configuration.getInstance().getString(
				"gpx.precision");
		return Long.parseLong(precisionString);
	}

	private long lengthBeetween(Date curTime, Date lastTime) {
		Calendar curCalendar = Calendar.getInstance();
		curCalendar.setTime(curTime);
		Calendar lastCalendar = Calendar.getInstance();
		lastCalendar.setTime(lastTime);
		return (curCalendar.getTimeInMillis() - lastCalendar.getTimeInMillis()) / 1000;
	}

}
