package br.edu.ifsul.livraria.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifsul.livraria.dao.TableFilter;

@FacesConverter(value = "tableFilterConverter")
public class TableFilterConverter implements Converter<TableFilter> {
	
	private List<TableFilter> filtrosDisponiveis;
	
	public TableFilterConverter(List<TableFilter> filtrosDisponiveis) {
		this.filtrosDisponiveis = filtrosDisponiveis;
	}

	@Override
	public TableFilter getAsObject(FacesContext context, UIComponent component, String value) {
		for (TableFilter tableFilter : filtrosDisponiveis) {
			if (tableFilter.getAtributo().equals(value)) {
				return tableFilter;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TableFilter value) {
		return (value != null) ? value.getAtributo() : null;  
	}

}
