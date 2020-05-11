package ar.com.flexibility.examen.domain.service;

public interface IProcessGenericEntityService<E> {
	E add(E entity);
	Iterable<E> getAll();
	E update(E entity);
	void delete(long id);
}
