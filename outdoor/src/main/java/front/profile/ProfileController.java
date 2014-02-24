package front.profile;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import front.profile.db.ProfileDBDelegate;

@ManagedBean(name = "profileController")
@SessionScoped
public class ProfileController implements Serializable {

	private static final long serialVersionUID = 5437755081676084416L;
	private final ProfileDBDelegate profileDBDelegate = new ProfileDBDelegate();

	private PersonBean personBean;

	private long idToRemove;
	private List<PersonBean> persons;

	public String createNew() {
		profileDBDelegate.create(personBean.toDBObject());
		FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Profile ajouté", null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		return "success";
	}

	public String listAll() {
		persons = profileDBDelegate.loadProfiles();
		return "listProfiles";
	}

	public String delete() {
		profileDBDelegate.delete(personBean);
		persons.remove(personBean);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Info", "Profile supprimé : " + personBean));
		return null;
	}

	public PersonBean getPersonBean() {
		return personBean;
	}

	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}

	public List<PersonBean> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonBean> persons) {
		this.persons = persons;
	}

	public long getIdToRemove() {
		return idToRemove;
	}

	public void setIdToRemove(long idToRemove) {
		this.idToRemove = idToRemove;
	}
}
