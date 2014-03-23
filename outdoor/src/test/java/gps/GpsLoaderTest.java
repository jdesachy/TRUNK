package gps;

import gps.description.GpxFile;
import gps.exception.GpsLoaderExcpetion;
import gps.exception.GpsWriterException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

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
		Assert.assertEquals(2, gpx.getTrk().getTrkseg().size());
		Assert.assertEquals("6.484033", gpx.getTrk().getTrkseg().get(0)
				.getLon());
		Assert.assertEquals("45.922770", gpx.getTrk().getTrkseg().get(0)
				.getLat());
		Assert.assertEquals("1447.00", gpx.getTrk().getTrkseg().get(0).getEle());
		Assert.assertEquals(9, gpx.getTrk().getTrkseg().get(0).getSat());
		Assert.assertEquals("2014-02-15T07:39:00.000Z",
				dateFormat.format(gpx.getTrk().getTrkseg().get(0).getTime()));
	}

	@Test
	public void testExportNewGpx() throws GpsWriterException,
			GpsLoaderExcpetion, UnsupportedEncodingException {
		String xml = loader.exportNewGpx(loader.convertFile());
		Assert.assertTrue("encoding xml attribut", xml.contains("encoding"));
		Assert.assertTrue(xml.contains("xmlns"));
		Assert.assertTrue(xml.contains(new String("Jérôme Desachy".getBytes(),
				"UTF-8")));
	}

}