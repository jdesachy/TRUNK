package gps;

import gps.description.Datas;
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
		GpxFile res = new GpxFile();
		res.setMetadata(fileToConvert.getMetadata());

		long interval = readConfigurationInterval();
		List<Datas> datas = fileToConvert.getDatas();
		for (Datas actualDatas : datas) {
			res.addData(buildNewDatas(interval, actualDatas));
		}
		return res;
	}

	private Datas buildNewDatas(long interval, Datas actualDatas) {
		List<Position> newPositions = buildNewPositions(actualDatas, interval);
		Datas d = new Datas();
		d.setTrkseg(newPositions);
		return d;
	}

	private List<Position> buildNewPositions(Datas datas, long interval) {
		List<Position> newPositions = new ArrayList<Position>();
		List<Position> positions = datas.getTrkseg();
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
