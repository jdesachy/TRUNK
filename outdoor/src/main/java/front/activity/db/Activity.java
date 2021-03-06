package front.activity.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import db.DBObject;
import front.profile.db.Person;

@Table
@Entity
public class Activity implements DBObject {

	@Id
	private long id;
	private String name;
	private Date date;
	private String massif;
	private int denivele;
	private String comment;
	private String type;
	private String gpx;
	private String album;

	private Set<Person> persons = new HashSet<Person>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMassif() {
		return massif;
	}

	public void setMassif(String massif) {
		this.massif = massif;
	}

	public int getDenivele() {
		return denivele;
	}

	public void setDenivele(int denivele) {
		this.denivele = denivele;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public String getGpx() {
		return gpx;
	}

	public void setGpx(String gpx) {
		this.gpx = gpx;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

}
