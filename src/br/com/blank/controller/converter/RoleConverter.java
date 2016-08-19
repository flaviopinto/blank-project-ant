package br.com.blank.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.blank.dao.RoleDao;
import br.com.blank.model.Role;

@FacesConverter(forClass = Role.class)
public class RoleConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {

		if (value == null)
			return null;		

		RoleDao dao = ctx.getApplication().evaluateExpressionGet(ctx,
				"#{perfilDao}", RoleDao.class);

		Role role = dao.load(value);
		return role;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {

		if (value == null) {
			return null;
		}

		Role role = (Role) value;
		return role.getName();
	}

}
