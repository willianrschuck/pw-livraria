package br.edu.ifsul.livraria.dao;

import java.util.HashMap;

import javax.ejb.Stateless;

import br.edu.ifsul.livraria.model.Livro;
import br.edu.ifsul.livraria.util.JasperUtil;

@Stateless
public class LivroDAO extends AbstractDAO<Livro> {

	@Override
	protected Class<Livro> getPersistentClass() {
		return Livro.class;
	}

	public void imprimir() {
		JasperUtil.imprimeRelatorio("relatorioLivro", new HashMap<>(), getLista());
	}

}
