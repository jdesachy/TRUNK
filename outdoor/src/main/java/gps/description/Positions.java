package gps.description;

import java.util.ArrayList;
import java.util.List;

public class Positions {

	private List<Position> trkpt = new ArrayList<Position>();

	public void add(Position p) {
		trkpt.add(p);
	}

	public List<Position> getTrkpt() {
		return trkpt;
	}

	public void setTrkpt(List<Position> trkpt) {
		this.trkpt = trkpt;
	}

}
