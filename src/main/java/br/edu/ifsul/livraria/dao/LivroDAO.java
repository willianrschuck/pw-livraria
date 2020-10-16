package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Livro;

@Stateless
public class LivroDAO extends AbstractDAO<Livro> {

	@Override
	protected Class<Livro> getPersistentClass() {
		return Livro.class;
	}

}
