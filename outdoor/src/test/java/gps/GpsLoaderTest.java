package gps;

import gps.description.Coordonnees;
import gps.description.Datas;
import gps.description.GpxFile;
import gps.description.Infos;
import gps.description.Person;
import gps.description.Position;
import gps.description.Positions;

import java.util.Calendar;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class GpsLoaderTest {

	@Test
	public void testCreateXml() {
		GpxFile file = new GpxFile();
		file.setMetadata(buildInfos());
		file.setTrk(buildDatas());
		XStream xstream = new XStream();
		xstream.alias("gpx", GpxFile.class);
		xstream.useAttributeFor(Coordonnees.class, "maxlon");
		xstream.useAttributeFor(Coordonnees.class, "maxlat");
		xstream.useAttributeFor(Coordonnees.class, "minlon");
		xstream.useAttributeFor(Coordonnees.class, "minlat");

		xstream.useAttributeFor(Position.class, "lon");
		xstream.useAttributeFor(Position.class, "lat");

		System.out.println(xstream.toXML(file));
	}

	private Datas buildDatas() {
		Datas datas = new Datas();
		datas.setTrkseg(buildPositions());
		return datas;
	}

	private Positions buildPositions() {
		Positions positions = new Positions();
		Position p = new Position(1.44, 43.55);
		p.setEle(1775.00);
		p.setSat(6);
		p.setTime(Calendar.getInstance().getTime());
		positions.add(p);
		return positions;
	}

	private Infos buildInfos() {
		Infos infos = new Infos();
		infos.setAuthor(buildPerson());
		infos.setBounds(buildCoord());
		infos.setTime(Calendar.getInstance());
		return infos;
	}

	private Coordonnees buildCoord() {
		return new Coordonnees(1.233, 23.444, 12.444, 54.9);
	}

	private Person buildPerson() {
		Person person = new Person();
		person.setName("Dupont Bernard");
		return person;
	}

}