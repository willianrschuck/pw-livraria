package br.edu.ifsul.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsul.livraria.converter.TableFilterConverter;

public class FiltroTabelaLazy<T> {

	private LazyTabelaDAO<T> lazyDao;
	
	private List<TableFilter> filtros = new ArrayList<>();
	private TableFilter filtroSelecionado;
	private String valorFiltro = "";
	private TableFilterConverter filterConverter;
	private Integer objetosPorPagina = 2;
	private Integer paginaAtual = 0;
	private Integer totalDeObjetos = 0;
	
	public FiltroTabelaLazy(LazyTabelaDAO<T> lazyDao) {
		this.lazyDao = lazyDao;
		this.filterConverter = new TableFilterConverter(filtros);
	}
	
	public List<T> getLista() {
		totalDeObjetos = lazyDao.count(paginaAtual, objetosPorPagina, filtroSelecionado, valorFiltro);
		return lazyDao.load(paginaAtual, objetosPorPagina, filtroSelecionado, valorFiltro);
	}
	
	public void primeiraPagina() {
		paginaAtual = 0;
	}
	
	public void paginaAnterior() {
		paginaAtual -= objetosPorPagina;
		if (paginaAtual < 0) {
			paginaAtual = 0;
		}
	}
	
	public void proximaPagina() {
		if (paginaAtual + objetosPorPagina < totalDeObjetos) {
			paginaAtual += objetosPorPagina;
		}
	}
	
	public void ultimaPagina() {
		Integer resto = totalDeObjetos % objetosPorPagina ;
		if (resto == 0) {
			paginaAtual = totalDeObjetos - objetosPorPagina;
		} else {
			paginaAtual = totalDeObjetos - resto;
		}
	}
	
	public String getMensagemNavegacao() {
		int ate = paginaAtual + objetosPorPagina;
		if (ate > totalDeObjetos) {
			ate = totalDeObjetos;
		}
		if (totalDeObjetos > 0) {
			return "Listando de " + (paginaAtual+1) + " até " + ate + " de " + totalDeObjetos + " registros ";
		} else {
			return "Nenhum registro encontrado.";
		}
		
	}

	public void addFiltro(TableFilter filter) {
		this.filtros.add(filter);
	}
	
	public List<TableFilter> getFiltros() {
		return filtros;
	}
	
	public TableFilterConverter getFilterConverter() {
		return filterConverter;
	}
	
	
	public TableFilter getFiltroSelecionado() {
		return filtroSelecionado;
	}
	
	public void setFiltroSelecionado(TableFilter filtroSelecionado) {
		this.filtroSelecionado = filtroSelecionado;
	}
	
	public String getValorFiltro() {
		return valorFiltro;
	}
	
	public void setValorFiltro(String valorFiltro) {
		this.valorFiltro = valorFiltro;
	}
	
	public Integer getObjetosPorPagina() {
		return objetosPorPagina;
	}
	
	public void setObjetosPorPagina(Integer objetosPorPagina) {
		this.objetosPorPagina = objetosPorPagina;
	}
	
	public Integer getTotalDeObjetos() {
		return totalDeObjetos;
	}
	
	public void setTotalDeObjetos(Integer totalDeObjetos) {
		this.totalDeObjetos = totalDeObjetos;
	}
	
	public Integer getPaginaAtual() {
		return paginaAtual;
	}
	
	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	
}
