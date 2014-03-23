package gps;

import gps.description.Coordonnees;
import gps.description.GpxFile;
import gps.description.Position;
import gps.description.converter.DateConverter;
import gps.exception.GpsLoaderExcpetion;
import gps.exception.GpsUploadException;
import gps.exception.GpsWriterException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import listener.Configuration;
import listener.exception.InitConfigurationException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.thoughtworks.xstream.XStream;

public class GpsLoader {

	private final InputStream stream;
	private final XStream xstream;

	public GpsLoader(InputStream stream) {
		this.stream = stream;
		xstream = new XStream();
		xstream.processAnnotations(GpxFile.class);
		xstream.processAnnotations(Coordonnees.class);
		xstream.processAnnotations(Position.class);
		xstream.registerConverter(new DateConverter());
	}

	public GpxFile convertFile() throws GpsLoaderExcpetion {
		GpxFile gpx = (GpxFile) xstream.fromXML(stream);
		gpx.setNamespace("http://www.topografix.com/GPX/1/1");
		gpx.setXmlSchema("http://www.w3.org/2001/XMLSchema-instance");
		gpx.setSchemaLocation("http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd");
		return gpx;
	}

	public String exportNewGpx(GpxFile gpx) throws GpsWriterException {
		GpsConverter converter = new GpsConverter();
		String xml = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Writer writer;
		try {
			writer = new OutputStreamWriter(outputStream, "ISO-8859-1");
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			xstream.toXML(converter.convert(gpx), writer);
			xml = new String(outputStream.toByteArray(), "UTF-8");
		} catch (Exception e) {
			throw new GpsWriterException(e);
		}
		return xml;
	}

	public void uploadFile(String xml, String fileName)
			throws GpsUploadException {
		FTPClient client = new FTPClient();
		try {
			client.connect(Configuration.getInstance().getString("ftp.host"));
			boolean login = client.login(
					Configuration.getInstance().getString("ftp.login"),
					Configuration.getInstance().getString("ftp.password"));

			if (login) {
				InputStream fis = new ByteArrayInputStream(xml.getBytes());
				String filePath = buildPath(fileName);
				client.setFileType(FTP.BINARY_FILE_TYPE);
				client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
				client.enterLocalPassiveMode();
				client.storeFile(filePath, fis);
				fis.close();
				client.logout();
			} else {
				throw new GpsUploadException();
			}
		} catch (Exception e) {
			throw new GpsUploadException(e);
		}

	}

	private String buildPath(String fileName) throws InitConfigurationException {
		String folder = Configuration.getInstance().getString(
				"ftp.directory.gps");
		return folder + "/" + fileName;
	}
}
