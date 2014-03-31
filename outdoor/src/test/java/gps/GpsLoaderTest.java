package gps;

import gps.description.Datas;
import gps.description.GpxFile;
import gps.description.Position;
import gps.exception.GpsLoaderExcpetion;
import gps.exception.GpsWriterException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GpsLoaderTest {

	private GpsLoader loader;

	@Before
	public void setup() throws FileNotFoundException {
		File file = new File("src/test/data/exercise-2014-2-15.gpx");
		FileInputStream in = new FileInputStream(file);
		loader = new GpsLoader(in);
	}

	@Test
	public void testConvertFile() throws GpsLoaderExcpetion,
			FileNotFoundException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		GpxFile gpx = loader.convertFile();
		Assert.assertNotNull(gpx);
		Assert.assertNotNull(gpx.getNamespace());
		Assert.assertNotNull(gpx.getXmlSchema());
		Assert.assertNotNull(gpx.getSchemaLocation());
		Assert.assertEquals("Jérôme Desachy", gpx.getMetadata().getAuthor()
				.getName());
		Assert.assertEquals("2014-02-16T14:15:14.777Z",
				dateFormat.format(gpx.getMetadata().getTime()));
		Assert.assertEquals(2, gpx.getDatas().get(0).getTrkseg().size());
		Assert.assertEquals("6.484033", gpx.getDatas().get(0).getTrkseg()
				.get(0).getLon());
		Assert.assertEquals("45.922770",
				gpx.getDatas().get(0).getTrkseg().get(0).getLat());
		Assert.assertEquals("1447.00", gpx.getDatas().get(0).getTrkseg().get(0)
				.getEle());
		Assert.assertEquals(9, gpx.getDatas().get(0).getTrkseg().get(0)
				.getSat());
		Assert.assertEquals(
				"2014-02-15T07:39:00.000Z",
				dateFormat.format(gpx.getDatas().get(0).getTrkseg().get(0)
						.getTime()));
		Assert.assertEquals(" GPS fix gained.", gpx.getDatas().get(1)
				.getTrkseg().get(1).getCmt());
	}

	@Test
	public void testExportNewGpx() throws GpsWriterException,
			GpsLoaderExcpetion, UnsupportedEncodingException {
		String xml = loader.exportNewGpx(buildGpx());
		System.out.println(xml);
		Assert.assertTrue("encoding xml attribut", xml.contains("encoding"));
	}

	private GpxFile buildGpx() {
		GpxFile gpxFile = new GpxFile();
		gpxFile.addData(buildDatas());
		gpxFile.addData(buildDatas());
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
		position.setCmt("GPS fix gained");
		return position;
	}

}