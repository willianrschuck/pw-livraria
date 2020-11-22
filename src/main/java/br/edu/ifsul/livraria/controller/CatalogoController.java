package br.edu.ifsul.livraria.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.CatalogoDAO;
import br.edu.ifsul.livraria.dao.FiltroTabelaLazy;
import br.edu.ifsul.livraria.dao.LivroDAO;
import br.edu.ifsul.livraria.dao.TableFilter;
import br.edu.ifsul.livraria.model.Catalogo;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@ViewScoped
public class CatalogoController extends AbstractController {
	private static final long serialVersionUID = 1L;
	
	@EJB private CatalogoDAO dao;
	@EJB private LivroDAO livroDao;
	
	private FiltroTabelaLazy<Catalogo> tabelaLazy;
	
	private Catalogo catalogo;
	
	@PostConstruct
	private void init() {
		tabelaLazy = new FiltroTabelaLazy<Catalogo>(dao);
		tabelaLazy.addFiltro(new TableFilter("id", "Id", "="));
	}
	
	@Override
	public void novo() {
		catalogo = new Catalogo();
	}

	@Override
	public void salvar() {
		try {
			if (catalogo.getId() == null) {
				dao.persist(catalogo);
			} else {
				dao.merge(catalogo);
			}
			JsfUtil.sendInfoMessage("Catálogo cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			catalogo = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			JsfUtil.sendInfoMessage("Catálogo removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public String irParaLista() {
		return "/catalogo/lista.xhtml?faces-redirect=true";
	}
	
	public Catalogo getCatalogo() {
		return catalogo;
	}
	
	public FiltroTabelaLazy<Catalogo> getTabelaLazy() {
		return tabelaLazy;
	}
	
}
