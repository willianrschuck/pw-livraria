package br.edu.ifsul.livraria.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_autor", sequenceName = "seq_autor_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_autor", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "bibliografia")
	private String bibliografia;
	
	public Autor() {}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBibliografia() {
		return bibliografia;
	}

	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Autor)) {
			return false;
		}
		Autor other = (Autor) obj;
		return Objects.equals(id, other.id);
	}

}
