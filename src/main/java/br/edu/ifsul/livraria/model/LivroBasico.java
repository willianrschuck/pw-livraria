package br.edu.ifsul.livraria.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datapublicacao")
	private Date dataPublicacao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "livrobasico_autores", 
		joinColumns = {@JoinColumn(name = "livrobasico_id")},
		inverseJoinColumns = {@JoinColumn(name = "autor_id")})
	private List<Autor> autores;
	
	public LivroBasico() {}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
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
