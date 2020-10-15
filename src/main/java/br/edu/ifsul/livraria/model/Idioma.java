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
@Table(name = "idioma")
public class Idioma {
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_idioma", sequenceName = "seq_idioma_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_idioma", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sigla")
	private String sigla;

	public Idioma() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		if (!(obj instanceof Idioma)) {
			return false;
		}
		Idioma other = (Idioma) obj;
		return Objects.equals(id, other.id);
	}
	
}
