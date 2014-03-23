package gps;

import gps.description.Datas;
import gps.description.GpxFile;
import gps.description.Position;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class GpsConverterTest {

	@Test
	public void testConvert() throws Exception {
		GpsConverter converter = new GpsConverter();
		GpxFile newGpx = converter.convert(buildGpx());
		Assert.assertEquals(2, newGpx.getTrk().getTrkseg().size());
	}

	private GpxFile buildGpx() {
		GpxFile gpxFile = new GpxFile();
		gpxFile.setTrk(buildDatas());
		return gpxFile;
	}

	private Datas buildDatas() {
		Datas datas = new Datas();
		datas.add(buildPositions(getTimeWithSecondOffset(10)));
		datas.add(buildPositions(getTimeWithSecondOffset(12)));
		datas.add(buildPositions(getTimeWithSecondOffset(14)));
		datas.add(buildPositions(getTimeWithSecondOffset(16)));
		datas.add(buildPositions(getTimeWithSecondOffset(18)));
		datas.add(buildPositions(getTimeWithSecondOffset(20)));
		return datas;
	}

	private Date getTimeWithSecondOffset(int i) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, i);
		return cal.getTime();
	}

	private Position buildPositions(Date time) {
		Position position = new Position("12.33", "40.4");
		position.setTime(time);
		return position;
	}
}
