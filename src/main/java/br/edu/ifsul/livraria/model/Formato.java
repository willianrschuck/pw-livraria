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
@Table(name = "formato")
public class Formato {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_formato", sequenceName = "seq_formato_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_formato", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	public Formato() {}

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
		if (!(obj instanceof Formato)) {
			return false;
		}
		Formato other = (Formato) obj;
		return Objects.equals(id, other.id);
	}
	
}
