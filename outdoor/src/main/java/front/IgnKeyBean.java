package front;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import listener.Configuration;
import listener.exception.InitConfigurationException;

@ManagedBean(name = "ignKey")
@RequestScoped
public class IgnKeyBean {

	private final Logger log = Logger.getLogger(IgnKeyBean.class.getName());
	private String value;

	public IgnKeyBean() {
		try {
			this.value = Configuration.getInstance().getString("ign.key");
		} catch (InitConfigurationException e) {
			log.log(Level.WARNING,
					"Impossible de récupérer la clé ign en configaration", e);
		}
	}

	public String getValue() {
		return value;
	}
}
