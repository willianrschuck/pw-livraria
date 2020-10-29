package br.edu.ifsul.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T> implements LazyTabelaDAO<T> {

	@PersistenceContext(unitName = "pu-livraria")
	private EntityManager em;
	
	public AbstractDAO() {}
	
	protected abstract Class<T> getPersistentClass();
	
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
	
	public T getBy(Object id) {
		return em.find(getPersistentClass(), id);
	}
	
	@Override
	public List<T> load(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue) {
		return em.createQuery(buildJpql(filtro, filterValue), getPersistentClass()).setFirstResult(firstValue).setMaxResults(maxResult).getResultList();
	}
	
	@Override
	public Integer count(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue) {
		return em.createQuery(buildJpql(filtro, filterValue), getPersistentClass()).getResultList().size();
	}
	
	private String buildJpql(TableFilter filter, String filterValue) {

		filterValue = filterValue.replace("[';-]", "");
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("FROM ").append(getPersistentClass().getSimpleName());
		
		if (!filterValue.trim().isEmpty()) {
			
			switch (filter.getOperador()) {
			case "=":
				if (filter.getAtributo().equalsIgnoreCase("id")) {
					try {
						Integer.parseInt(filterValue);
					} catch (Exception e) {
						filterValue = "0";
					}
				}
				jpql.append(" WHERE ").append(filter.getAtributo()).append(" = ").append(filterValue);
				break;
				
			case "like":
				jpql.append(" WHERE upper(").append(filter.getAtributo()).append(") LIKE '").append(filterValue.toUpperCase()).append("%'");
				break;

			default:
				break;
			}

			jpql.append(" ORDER BY ").append(filter.getAtributo());
			
		}
		
		return jpql.toString();
		
	}
	
}
