package br.edu.ifsul.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifsul.livraria.converter.TableFilterConverter;

public abstract class AbstractDAO<T> {

	@PersistenceContext(unitName = "pu-livraria")
	private EntityManager em;
	
	/* -- Atributos da lista lazy -- */
	protected List<TableFilter> filtros = new ArrayList<>();
	protected TableFilter filtroSelecionado;
	protected String valorFiltro = "";
	protected TableFilterConverter filterConverter;
	protected Integer objetosPorPagina = 2;
	protected Integer paginaAtual = 0;
	protected Integer totalDeObjetos = 0;
	
	public AbstractDAO() {}
	
	protected abstract Class<T> getPersistentClass();
	
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
	
	public void persist(T object) {
		em.persist(object);
	}
	
	public T merge(T object) {
		return em.merge(object);
	}
	
	public void remove(T object) {
		em.remove(em.merge(object));
	}
	
	public List<T> getLista() {
		String jpql = "FROM " + getPersistentClass().getSimpleName();
		return em.createQuery(jpql, getPersistentClass()).getResultList();
	}
	
	private String buildJpql(TableFilter filter, String filterValue) {

		filterValue = filterValue.replace("[';-]", "");
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("FROM ").append(getPersistentClass().getSimpleName());
		
		if (!filterValue.trim().isEmpty()) {
			
			switch (filter.getOperador()) {
			case "=":
				if (filtroSelecionado.getAtributo().equalsIgnoreCase("id")) {
					try {
						Integer.parseInt(filterValue);
					} catch (Exception e) {
						filterValue = "0";
					}
					jpql.append(" WHERE ").append(filtroSelecionado.getAtributo()).append(" = ").append(valorFiltro);
				}
				break;
				
			case "like":
				jpql.append(" WHERE upper(").append(filtroSelecionado.getAtributo()).append(") LIKE '").append(valorFiltro.toUpperCase()).append("%'");
				break;

			default:
				break;
			}

			jpql.append(" ORDER BY ").append(filtroSelecionado.getAtributo());
			
		}
		
		return jpql.toString();
		
		
	}
	
	public List<T> load(String jpql, Integer firstResult, Integer maxResults) {
		return em.createQuery(jpql, getPersistentClass()).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}
	
	public List<T> getListaLazy() {
		
		String jpql = buildJpql(filtroSelecionado, valorFiltro);
		
		totalDeObjetos = em.createQuery(jpql).getResultList().size();
		return load(jpql, paginaAtual, objetosPorPagina);
		
	}
	
	public T getBy(Object id) {
		return em.find(getPersistentClass(), id);
	}
	
	public void addFiltro(TableFilter filtro) {
		this.filtros.add(filtro);
	}

	public List<TableFilter> getFiltros() {
		return filtros;
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

	public TableFilterConverter getFilterConverter() {
		return filterConverter;
	}

	public void setFilterConverter(TableFilterConverter filterConverter) {
		this.filterConverter = filterConverter;
	}

	public Integer getObjetosPorPagina() {
		return objetosPorPagina;
	}

	public void setObjetosPorPagina(Integer objetosPorPagina) {
		this.objetosPorPagina = objetosPorPagina;
	}

	public Integer getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public Integer getTotalDeObjetos() {
		return totalDeObjetos;
	}

	public void setTotalDeObjetos(Integer totalDeObjetos) {
		this.totalDeObjetos = totalDeObjetos;
	}

	public void setFiltros(List<TableFilter> filtros) {
		this.filtros = filtros;
	}
	
	
	
}
