package br.edu.ifsul.livraria.dao;

import javax.annotation.security.DenyAll;
import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Permissao;

@Stateless
public class PermissaoDAO extends AbstractDAO<Permissao> {

	@Override
	protected Class<Permissao> getPersistentClass() {
		return Permissao.class;
	}
	
	@Override
	@DenyAll
	public void persist(Permissao object) {
		super.persist(object);
	}
	
	@Override
	@DenyAll
	public Permissao merge(Permissao object) {
		return super.merge(object);
	}
	
	@Override
	@DenyAll
	public void remove(Permissao object) {
		super.remove(object);
	}
	
}
