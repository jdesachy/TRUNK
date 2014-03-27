package gps.description;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("trkpt")
public class Position {

	@XStreamAsAttribute
	private final String lon;
	@XStreamAsAttribute
	private final String lat;
	private String ele;
	private Date time;
	private int sat;
	private String cmt;

	public Position(String lon, String lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public String getLat() {
		return lat;
	}

	public String getEle() {
		return ele;
	}

	public void setEle(String ele) {
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

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

}
