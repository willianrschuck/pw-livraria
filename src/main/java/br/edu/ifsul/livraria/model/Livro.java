package br.edu.ifsul.livraria.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro extends LivroBasico {

	@Column(name = "codigobarras")
	private String codigoBarras;
	
	@Column(name = "numeropaginas")
	private Integer numeroPaginas;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "datacadastro")
	private Calendar dataCadastro;
	
	@Column(name = "valor")
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "idioma_id")
	private Idioma idioma;
	
	@ManyToOne
	@JoinColumn(name = "formato_id")
	private Formato formato;
	
	@ManyToOne
	@JoinColumn(name = "catalogo_id")
	private Catalogo catalogo;
	
	public Livro() {}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
