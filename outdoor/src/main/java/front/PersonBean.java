package front;

import java.io.Serializable;

public class PersonBean implements Serializable {

	private static final long serialVersionUID = -7600290562999743296L;

	private String firstName;
	private String lastName;

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

}
