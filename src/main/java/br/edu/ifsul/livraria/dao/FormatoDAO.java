package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Formato;

@Stateless
public class FormatoDAO extends AbstractDAO<Formato> {

	public FormatoDAO() {}
	
	@Override
	protected Class<Formato> getPersistentClass() {
		return Formato.class;
	}
	
}
