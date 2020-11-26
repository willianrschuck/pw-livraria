package br.edu.ifsul.livraria.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.AutorDAO;
import br.edu.ifsul.livraria.model.Autor;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@ViewScoped
public class AutorController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@EJB private AutorDAO dao;
	
	private Autor autor;

	private List<Autor> lista;
	
	@Override
	public void novo() {
		autor = new Autor();
	}

	@Override
	public void salvar() {
		try {
			if (autor.getId() == null) {
				dao.persist(autor);
			} else {
				dao.merge(autor);
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
			autor = dao.getBy(id);
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
		return "/app/autor/lista.xhtml?faces-redirect=true";
	}
	
	private void limparLista() {
		lista = null;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getLista() {
		if (lista == null) {
			lista = dao.getLista();
		}
		return lista;
	}
}
