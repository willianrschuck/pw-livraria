package br.edu.ifsul.livraria.dao;

import java.io.Serializable;

public class TableFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String atributo;
	private String label;
	private String operador;
	
	public TableFilter(String atributo, String label, String operador) {
		this.atributo = atributo;
		this.label = label;
		this.operador = operador;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return label;
	}

}
