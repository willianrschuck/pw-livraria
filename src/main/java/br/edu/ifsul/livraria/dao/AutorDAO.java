package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Autor;

@Stateless
public class AutorDAO extends AbstractDAO<Autor> {

	@Override
	protected Class<Autor> getPersistentClass() {
		return Autor.class;
	}

}
