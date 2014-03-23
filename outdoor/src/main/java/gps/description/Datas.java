package gps.description;

import java.util.ArrayList;
import java.util.List;

public class Datas {

	private List<Position> trkseg = new ArrayList<Position>();

	public void add(Position p) {
		trkseg.add(p);
	}

	public List<Position> getTrkseg() {
		return trkseg;
	}

	public void setTrkseg(List<Position> trkseg) {
		this.trkseg = trkseg;
	}

}
