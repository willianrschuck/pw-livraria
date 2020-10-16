package br.edu.ifsul.livraria.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.FormatoDAO;
import br.edu.ifsul.livraria.model.Formato;

@Named
public class FormatoConverter implements Converter<Formato> {

	@EJB private FormatoDAO dao;
	
	@Override
	public Formato getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return dao.getBy(Integer.parseInt(value));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Formato value) {
		if (value != null && value.getId() != null) {
			return value.getId().toString();
		}
		return "";
	}

}
