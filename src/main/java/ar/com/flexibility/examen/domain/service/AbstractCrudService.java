package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.BaseObjectNotFoundException;
import ar.com.flexibility.examen.domain.model.BaseObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base CRUD service for domain entities
 * @param <E> a business entity extended from {@link BaseObject}
 * @param <ID> the ID object class
 */
@Component
public abstract class AbstractCrudService<E extends BaseObject, ID extends Serializable> {


	protected CrudRepository <E, ID> repository;


	public E create(E entity) throws Throwable {
		entity.setDeleted(Boolean.FALSE);
		Date creationDate = new Date();
		entity.setCreationDate(creationDate);
		entity.setLastUpdate(creationDate);

		return repository.save(entity);
	}


	public E get(ID id) throws BaseObjectNotFoundException {
		E entity = repository.findOne(id);
		if (null == entity || entity.isDeleted()) {
			throw new BaseObjectNotFoundException();
		}
		return entity;
	}

	public List<E> getAll() {
		List<E> result = (List<E>) repository.findAll();
		return result.stream().filter(p -> !p.isDeleted()).collect(Collectors.toList());
	}

	public E update(ID id, E entity) throws BaseObjectNotFoundException {
		E previous = repository.findOne(id);

		if (null == previous || previous.isDeleted()) {
			throw new BaseObjectNotFoundException();
		}

		entity.setId(previous.getId());
		entity.setLastUpdate(new Date());
		entity.setCreationDate(previous.getCreationDate());
		entity.setDeleted(previous.isDeleted());

		return repository.save(entity);
	}

	public void delete(ID id) throws BaseObjectNotFoundException {
		E toDelete = repository.findOne(id);

		if (null == toDelete || toDelete.isDeleted()) {
			throw new BaseObjectNotFoundException();
		}

		toDelete.setDeleted(Boolean.TRUE);
		repository.save(toDelete);
	}

}
