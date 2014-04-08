package front.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("CommentConverter")
public class CommentConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String string = (String) value;
		int maxlength = Integer.valueOf((String) component.getAttributes().get(
				"maxlength"));

		if (string.length() > maxlength) {
			return string.substring(0, maxlength) + "...";
		} else {
			return string;
		}
	}

}
