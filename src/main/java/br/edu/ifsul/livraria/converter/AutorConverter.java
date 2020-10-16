package br.edu.ifsul.livraria.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.AutorDAO;
import br.edu.ifsul.livraria.model.Autor;

@Named
public class AutorConverter implements Converter<Autor> {

	@EJB private AutorDAO dao;
	
	@Override
	public Autor getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return dao.getBy(Integer.parseInt(value));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Autor value) {
		if (value != null && value.getId() != null) {
			return value.getId().toString();
		}
		return "";
	}

}
