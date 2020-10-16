package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Idioma;

@Stateless
public class IdiomaDAO extends AbstractDAO<Idioma> {

	@Override
	protected Class<Idioma> getPersistentClass() {
		return Idioma.class;
	}

}
