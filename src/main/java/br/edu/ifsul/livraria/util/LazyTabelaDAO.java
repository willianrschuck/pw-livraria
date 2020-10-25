package br.edu.ifsul.livraria.util;

import java.util.List;

import br.edu.ifsul.livraria.dao.TableFilter;

public interface LazyTabelaDAO<T> {

	List<T> load(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue);
	
	Integer count(Integer firstValue, Integer maxResult, TableFilter filtro, String filterValue);
	
}
