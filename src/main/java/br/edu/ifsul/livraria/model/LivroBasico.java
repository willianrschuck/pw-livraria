package br.edu.ifsul.livraria.model;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "livrobasico")
@Inheritance(strategy = InheritanceType.JOINED)
public class LivroBasico {

	@Id
	@Column(name = "isbn")
	private String isbn;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "resumo")
	private String resumo;
	
	@Column(name = "editora")
	private String editora;
	
	@Column(name = "datapublicacao")
	private Calendar dataPublicacao;
	
	public LivroBasico() {}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LivroBasico)) {
			return false;
		}
		LivroBasico other = (LivroBasico) obj;
		return Objects.equals(isbn, other.isbn);
	}
	
}
