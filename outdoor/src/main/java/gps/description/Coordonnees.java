package gps.description;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Coordonnees {

	@XStreamAsAttribute
	private double maxlon;
	@XStreamAsAttribute
	private double maxlat;
	@XStreamAsAttribute
	private double minlon;
	@XStreamAsAttribute
	private double minlat;

	public Coordonnees() {
	}

	public double getMaxlon() {
		return maxlon;
	}

	public double getMaxlat() {
		return maxlat;
	}

	public double getMinlon() {
		return minlon;
	}

	public double getMinlat() {
		return minlat;
	}

	public void setMaxlon(double maxlon) {
		this.maxlon = maxlon;
	}

	public void setMaxlat(double maxlat) {
		this.maxlat = maxlat;
	}

	public void setMinlon(double minlon) {
		this.minlon = minlon;
	}

	public void setMinlat(double minlat) {
		this.minlat = minlat;
	}

}
