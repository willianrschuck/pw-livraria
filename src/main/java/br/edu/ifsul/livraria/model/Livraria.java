package br.edu.ifsul.livraria.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "livraria")
public class Livraria {
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_livraria", sequenceName = "seq_livraria_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_livraria", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "site")
	private String site;
	
	@OneToMany(mappedBy = "livraria")
	private List<Catalogo> catalogo;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Livraria)) {
			return false;
		}
		Livraria other = (Livraria) obj;
		return Objects.equals(id, other.id);
	}
	
}
