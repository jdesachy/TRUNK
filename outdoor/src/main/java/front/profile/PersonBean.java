package front.profile;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import db.Bean;
import front.profile.db.Person;

@ManagedBean(name = "personBean")
@ViewScoped
public class PersonBean implements Serializable, Bean {

	private static final long serialVersionUID = -7600290562999743296L;

	private long id;
	private String firstName;
	private String lastName;

	public PersonBean() {
	}

	public PersonBean(Person person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Person toDBObject() {
		Person person = new Person();
		person.setId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
