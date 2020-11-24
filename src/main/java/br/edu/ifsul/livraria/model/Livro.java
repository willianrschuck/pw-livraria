package br.edu.ifsul.livraria.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "livro")
public class Livro extends LivroBasico {

	@Column(name = "codigobarras")
	private String codigoBarras;
	
	@Column(name = "numeropaginas")
	private Integer numeroPaginas;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datacadastro")
	private Date dataCadastro;
	
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
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "livro_imagem", 
		joinColumns = {@JoinColumn(name = "livro_id")},
		inverseJoinColumns = {@JoinColumn(name = "imagem_id")})
	private List<Imagem> imagens = new ArrayList<Imagem>();
	
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Idioma getIdioma() {
		return idioma;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public Formato getFormato() {
		return formato;
	}
	
	public void setFormato(Formato formato) {
		this.formato = formato;
	}
	
	public List<Imagem> getImagens() {
		return imagens;
	}
	
	public void adicionarImagem(Imagem imagem) {
		if (imagens == null) {
			imagens = new ArrayList<>();
		}
		imagens.add(imagem);
	}
	
	public void removerImagem(int index) {
		if (imagens == null) {
			return;
		}
		imagens.remove(index);
	}
	
}
