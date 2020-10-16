package br.edu.ifsul.livraria.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.IdiomaDAO;
import br.edu.ifsul.livraria.model.Idioma;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@ViewScoped
public class IdiomaController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@EJB private IdiomaDAO dao;
	
	private Idioma idioma;

	private List<Idioma> lista;
	
	@Override
	public void novo() {
		idioma = new Idioma();
	}

	@Override
	public void salvar() {
		try {
			if (idioma.getId() == null) {
				dao.persist(idioma);
			} else {
				dao.merge(idioma);
			}
			limparLista();
			JsfUtil.sendInfoMessage("Idioma cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			idioma = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			limparLista();
			JsfUtil.sendInfoMessage("Idioma removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	@Override
	public String irParaLista() {
		return "/idioma/lista.xhtml?faces-redirect=true";
	}

	public Idioma getIdioma() {
		return idioma;
	}
	
	public List<Idioma> getLista() {
		if (lista == null) {
			lista = dao.getListaTodos();
		}
		return lista;
	}
	
	private void limparLista() {
		lista = null;
	}
	
}
