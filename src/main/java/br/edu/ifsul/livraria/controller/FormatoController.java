package br.edu.ifsul.livraria.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.FormatoDAO;
import br.edu.ifsul.livraria.model.Formato;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@ViewScoped
public class FormatoController extends AbstractController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB private FormatoDAO dao;
	
	private Formato formato;

	private List<Formato> lista;
	
	@Override
	public void novo() {
		formato = new Formato();
	}

	@Override
	public void salvar() {
		try {
			if (formato.getId() == null) {
				dao.persist(formato);
			} else {
				dao.merge(formato);
			}
			limparLista();
			JsfUtil.sendInfoMessage("Formato cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			formato = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			limparLista();
			JsfUtil.sendInfoMessage("Formato removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	@Override
	public String irParaLista() {
		return "/formato/lista.xhtml?faces-redirect=true";
	}

	public Formato getFormato() {
		return formato;
	}
	
	public List<Formato> getLista() {
		if (lista == null) {
			lista = dao.getListaLazy();
		}
		return lista;
	}
	
	private void limparLista() {
		lista = null;
	}
	
	// TODO Refatorar e remover o acesso direto ao DAO
	public FormatoDAO getDao() {
		return dao;
	}
	
}
