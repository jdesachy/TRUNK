package front.profile;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ProfileConverter")
public class ProfileConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		PersonBean personBean = new PersonBean();
		personBean.setId(new Integer(arg2));
		return personBean;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return String.valueOf(arg2);
	}

}
