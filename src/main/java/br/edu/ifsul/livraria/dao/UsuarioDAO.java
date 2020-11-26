package br.edu.ifsul.livraria.dao;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Usuario;

@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

	@Override
	protected Class<Usuario> getPersistentClass() {
		return Usuario.class;
	}

}
