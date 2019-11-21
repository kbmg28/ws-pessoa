package br.com.kbmg.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GenericService<T> {

	public T create(T entity);

	public T update(T entity, Long id);

	public T findById(String id, String nomeDoIdDaClasse);

	public List<T> findAll();

	public Page<T> findAllPaginated(int page, int size, Sort.Direction direction, String sortProperty);

	public void delete(T entity);

	public void deleteById(Long id);

	public void deleteAll(Set<T> entitys);

	public void deleteAllById(Set<Long> id);

}
