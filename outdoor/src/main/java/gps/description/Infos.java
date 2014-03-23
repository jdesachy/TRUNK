package gps.description;

import java.util.Date;

public class Infos {

	private Person author;
	private Date time;
	private Coordonnees bounds;

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Coordonnees getBounds() {
		return bounds;
	}

	public void setBounds(Coordonnees bounds) {
		this.bounds = bounds;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
