package br.edu.ifsul.livraria.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.edu.ifsul.livraria.dao.UsuarioDAO;
import br.edu.ifsul.livraria.model.Usuario;

@Named
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuarioAutenticado;
	@EJB private UsuarioDAO dao;
	
	private String username;
	private String password;
	
	
	public LoginController() {}


	public String irPaginaLogin() {
		return "/login?faces-redirect=true";
	}
	
	public String efetuarLogin() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.login(username, password);
			if (request.getUserPrincipal() != null) {
				usuarioAutenticado = dao.getBy(request.getUserPrincipal().getName());
			}
			username = "";
			password = "";
			return "/index";
		} catch (Exception e) {
			return "/login";
		}
	}
	
	public String efetuarLogout() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.logout();
			usuarioAutenticado = null;
			return "/index";
		} catch (Exception e) {
			return "/login";
		}
	}
	
	
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
		this.dao = dao;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
