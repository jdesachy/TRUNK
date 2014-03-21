package gps.description;

import java.util.Date;

public class Position {

	private final double lon;
	private final double lat;
	private double ele;
	private Date time;
	private int sat;

	public Position(double lon, double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}

	public double getEle() {
		return ele;
	}

	public void setEle(double ele) {
		this.ele = ele;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
