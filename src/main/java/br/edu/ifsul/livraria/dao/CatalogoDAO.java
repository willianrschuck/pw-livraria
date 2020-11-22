package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Catalogo;

@Stateless
public class CatalogoDAO extends AbstractDAO<Catalogo> {

	@Override
	protected Class<Catalogo> getPersistentClass() {
		return Catalogo.class;
	}

}
