package br.edu.ifsul.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDAO<T> {

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
	
	public List<T> getListaTodos() {
		String jpql = "FROM " + getPersistentClass().getSimpleName();
		return em.createQuery(jpql, getPersistentClass()).getResultList();
	}
	
	public T getBy(Object id) {
		return em.find(getPersistentClass(), id);
	}
	
}
