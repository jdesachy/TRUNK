package gps.description;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("trkseg")
public class Datas {

	@XStreamImplicit
	private List<Position> segment = new ArrayList<Position>();

	public void add(Position p) {
		segment.add(p);
	}

	public List<Position> getTrkseg() {
		return segment;
	}

	public void setTrkseg(List<Position> trkseg) {
		this.segment = trkseg;
	}

}
