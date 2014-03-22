package gps.description;

import java.util.Calendar;

public class Infos {

	private Person author;
	private Calendar time;
	private Coordonnees bounds;

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public Coordonnees getBounds() {
		return bounds;
	}

	public void setBounds(Coordonnees bounds) {
		this.bounds = bounds;
	}

}
