package gps.description;

public class Coordonnees {

	private final double maxlon;
	private final double maxlat;
	private final double minlon;
	private final double minlat;

	public Coordonnees(double maxlon, double maxlat, double minlon,
			double minlat) {
		super();
		this.maxlon = maxlon;
		this.maxlat = maxlat;
		this.minlon = minlon;
		this.minlat = minlat;
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

}
