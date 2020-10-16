package br.edu.ifsul.livraria.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.AutorDAO;
import br.edu.ifsul.livraria.dao.LivroDAO;
import br.edu.ifsul.livraria.model.Autor;
import br.edu.ifsul.livraria.model.Livro;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@ViewScoped
public class LivroController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@EJB private LivroDAO dao;
	@EJB private AutorDAO autorDao;
	
	private Livro livro;

	private List<Livro> lista;

	private List<Autor> autores;
	
	@Override
	public void novo() {
		livro = new Livro();
	}

	@Override
	public void salvar() {
		try {
			if (livro.getDataCadastro() == null) {
				livro.setDataCadastro(new Date());
			} else {
				dao.merge(livro);
			}
			limparLista();
			JsfUtil.sendInfoMessage("Autor cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			livro = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			limparLista();
			JsfUtil.sendInfoMessage("Autor removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	@Override
	public String irParaLista() {
		return "/livro/lista.xhtml?faces-redirect=true";
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLista() {
		if (lista == null) {
			lista = dao.getListaTodos();
		}
		return lista;
	}
	
	private void limparLista() {
		lista = null;
	}
	
	public List<Autor> completeAutor(String query) {
		if (autores == null) {
			autores = autorDao.getListaTodos();
		}
		List<Autor> autoresResult = new ArrayList<>(5);
		for (Autor autor : autores) {
			if (autor.getNome().toLowerCase().contains(query.toLowerCase())) {
				autoresResult.add(autor);
				if (autoresResult.size() == 5) {
					break;
				}
			}
		}
		return autoresResult;
	}
	
}
