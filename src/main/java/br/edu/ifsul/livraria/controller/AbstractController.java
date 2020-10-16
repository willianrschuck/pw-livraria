package br.edu.ifsul.livraria.controller;

import java.io.Serializable;

public abstract class AbstractController implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract void novo();
	
	public abstract void salvar();
	
	public abstract void editar(Object id);
	
	public abstract void remover(Object id);
	
	public abstract String irParaLista();
	
}
