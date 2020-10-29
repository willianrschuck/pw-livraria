package br.edu.ifsul.livraria.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.AutorDAO;
import br.edu.ifsul.livraria.dao.FiltroTabelaLazy;
import br.edu.ifsul.livraria.dao.LivroDAO;
import br.edu.ifsul.livraria.dao.TableFilter;
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

	private FiltroTabelaLazy<Livro> tabelaLazy;

	private List<Autor> autores;
	private List<Autor> autoresSelecionados;

	
	@PostConstruct
	private void init() {
		tabelaLazy = new FiltroTabelaLazy<Livro>(dao);
		tabelaLazy.addFiltro(new TableFilter("isbn", "Isbn", "like"));
		tabelaLazy.addFiltro(new TableFilter("titulo", "Titulo", "like"));
		tabelaLazy.addFiltro(new TableFilter("formato.nome", "Formato", "like"));
	}
	
	@Override
	public void novo() {
		livro = new Livro();
	}

	@Override
	public void salvar() {
		try {
			if (livro.getDataCadastro() == null) {
				livro.setDataCadastro(new Date());
				dao.persist(livro);
			} else {
				dao.merge(livro);
			}
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
			JsfUtil.sendInfoMessage("Autor removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	@Override
	public String irParaLista() {
		return "/livro/lista.xhtml?faces-redirect=true";
	}
	
	public void removerAutor(int index) {
		livro.getAutores().remove(index);
	}
	
	public List<Autor> getAutores() {
		if (autores == null) {
			autores = autorDao.getLista();
		}
		return autores;
	}

	public List<Autor> getAutoresSelecionados() {
		return autoresSelecionados;
	}
	
	public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
		this.autoresSelecionados = autoresSelecionados;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public FiltroTabelaLazy<Livro> getTabelaLazy() {
		return tabelaLazy;
	}
	
}
