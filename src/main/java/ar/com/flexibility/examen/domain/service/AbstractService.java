package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.GenericEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class AbstractService<T extends GenericEntity, S extends CrudRepository> implements GenericService<T> {

    protected S repository;

    public AbstractService(S repository) {
        this.repository = repository;
    }

    public T findCheckedEntity(Long id) throws EntityNotFoundException {
        return (T) Optional
                .ofNullable(repository.findOne(id))
                .filter(object->{
                    T entity = (T) object;
                    return !entity.isDeleted();
                })
                .orElseThrow(
                        ()-> new EntityNotFoundException(String.format("Could not find entity driver with id: %s", id)));
    }

    public T createEntity(T entity) throws ConstraintsViolationException {
        try {
            if(Optional.ofNullable(repository.findOne(entity.getId())).isPresent()) {
                throw new ConstraintsViolationException(
                        String.format("Entity with id %s already exists", entity.getId()));
            }
            return (T) repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintsViolationException(e.getMessage());
        }
    }

    public T deleteEntity(Long id) throws EntityNotFoundException, ConstraintsViolationException {
        try {
            T entity = findCheckedEntity(id);
            entity.setDeleted(true);
            return (T) repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintsViolationException(e.getMessage());
        }
    }

    @Override
    public T findOne(Long id) throws EntityNotFoundException {
        return findCheckedEntity(id);
    }


    @Override
    public T create(T entity) throws ConstraintsViolationException {
        return createEntity(entity);
    }

    @Override
    public T save(T entity) throws EntityNotFoundException {
        findCheckedEntity(entity.getId());
        return (T) repository.save(entity);
    }

    @Override
    public T delete(Long id) throws EntityNotFoundException, ConstraintsViolationException {
        return deleteEntity(id);
    }
}