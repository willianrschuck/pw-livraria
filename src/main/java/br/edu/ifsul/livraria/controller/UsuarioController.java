package br.edu.ifsul.livraria.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifsul.livraria.dao.FiltroTabelaLazy;
import br.edu.ifsul.livraria.dao.PermissaoDAO;
import br.edu.ifsul.livraria.dao.UsuarioDAO;
import br.edu.ifsul.livraria.model.Permissao;
import br.edu.ifsul.livraria.model.Usuario;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@SessionScoped
public class UsuarioController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@EJB private UsuarioDAO dao;
	@EJB private PermissaoDAO permissaoDao;
	
	private Usuario usuario;
	
	private FiltroTabelaLazy<Usuario> tabelaLazy;
	
	private List<Permissao> permissoesExistentes;

	@PostConstruct
	private void init() {
		tabelaLazy = new FiltroTabelaLazy<Usuario>(dao);
//		tabelaLazy.addFiltro(new TableFilter("isbn", "Isbn", "like"));
//		tabelaLazy.addFiltro(new TableFilter("formato.nome", "Formato", "like"));
	}
	
	@Override
	public void novo() {
		usuario = new Usuario();
	}

	@Override
	public void salvar() {
		try {
			dao.merge(usuario);
			JsfUtil.sendInfoMessage("Usuario cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			usuario = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			JsfUtil.sendInfoMessage("Usuario removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	public void removerPermissao(int index) {
		this.usuario.getPermissoes().remove(index);
	}
	
	@Override
	public String irParaLista() {
		return "/app/usuario/lista.xhtml?faces-redirect=true";
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public FiltroTabelaLazy<Usuario> getTabelaLazy() {
		return tabelaLazy;
	}

	public List<Permissao> getPermissoesExistentes() {
		if (permissoesExistentes == null) {
			permissoesExistentes = permissaoDao.getLista();
		}
		return permissoesExistentes;
	}
	
}
