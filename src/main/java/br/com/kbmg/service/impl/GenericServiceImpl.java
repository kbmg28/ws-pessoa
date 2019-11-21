package br.com.kbmg.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kbmg.config.MessagesService;
import br.com.kbmg.service.GenericService;
import br.com.kbmg.utils.Validator;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

	@Autowired
	private JpaRepository<T, Long> repository;

	@Autowired
	public MessagesService msg;

	@Override
	public T create(T entity) {
		return repository.save(entity);
	}

	@Override
	public T update(T entity, Long id) {

		if (id == null)
			throw new IllegalArgumentException(msg.get("id.obrigatorio"));

		this.findById(id.toString(), "ID");

		return repository.save(entity);
	}

	@Override
	public T findById(String id, String nomeDoIdDaClasse) {
		return repository.findById(Validator.stringParseLong(id, nomeDoIdDaClasse))
				.orElseThrow(() -> new EntityNotFoundException(msg.get("nao.encontrado")));
	}

	@Override
	public List<T> findAll() {
		List<T> list = repository.findAll();

		if (list == null)
			throw new EntityNotFoundException(msg.get("nao.encontrado"));

		return list;
	}

	@Override
	public Page<T> findAllPaginated(int page, int size, Direction direction, String sortProperty) {
		return repository.findAll(PageRequest.of(page, size, direction, sortProperty));
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		this.findById(id.toString(), "ID ");
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(Set<T> entitys) {
		repository.deleteAll(entitys);
	}

	@Override
	public void deleteAllById(Set<Long> ids) {
		Set<Long> notExist = new LinkedHashSet<>();

		ids.forEach(id -> {
			if (!repository.findById(id).isPresent())
				notExist.add(id);
		});

		if (!notExist.isEmpty())
			throw new EntityNotFoundException(msg.get("nao.encontrado") + notExist.toString());

		ids.forEach(id -> repository.deleteById(id));
	}
}
