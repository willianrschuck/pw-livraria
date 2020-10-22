package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateful;

import br.edu.ifsul.livraria.converter.TableFilterConverter;
import br.edu.ifsul.livraria.model.Formato;

// TODO Refatorar para o DAO não armazenar informações sobre o estado dos objetos
@Stateful
public class FormatoDAO extends AbstractDAO<Formato> {

	public FormatoDAO() {
		filtros.add(new TableFilter("id", "ID", "="));
		filtros.add(new TableFilter("nome", "Nome", "like"));
		filtroSelecionado = filtros.get(0);
		filterConverter = new TableFilterConverter(filtros);
	}
	
	@Override
	protected Class<Formato> getPersistentClass() {
		return Formato.class;
	}
	
}
