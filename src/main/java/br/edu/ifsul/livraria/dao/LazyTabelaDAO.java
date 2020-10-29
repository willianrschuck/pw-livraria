package br.edu.ifsul.livraria.dao;

import java.util.List;

public interface LazyTabelaDAO<T> {

	List<T> load(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue);
	
	Integer count(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue);
	
}
