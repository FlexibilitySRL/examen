package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.GenericEntity;

import java.util.List;

/**
 * Represents the service that manages the {@link T} operations.
 */
public interface GenericService<T extends GenericEntity> {

    /**
     * Returns a {@link T} entity present in database.
     * @param id {@link T} id.
     * @return requested {@link T}
     * @throws EntityNotFoundException if the {@link T} with id does not exists in database.
     */
    T findOne(Long id) throws EntityNotFoundException;

    /**
     * Lists all the {@link T} present in database.
     * @return {@link T} registered in database.
     */
    List<T> listAll();

    /**
     * Creates a {@link T} entity in database.
     * @param entity {@link T} to been persisted in database.
     * @return created {@link T}
     * @throws ConstraintsViolationException if something goes wrong.
     */
    T create(T entity) throws ConstraintsViolationException;

    /**
     * Updates a {@link T} entity present in database.
     * @param entity {@link T} to been persisted in database.
     * @return updated {@link T}
     * @throws EntityNotFoundException if the {@link T} with id does not exists in database.
     */
    T save(T entity) throws EntityNotFoundException;

    /**
     * Deletes a {@link T} entity in database.
     * The delete process consists in mark the entity as deleted, does not mean a physical deletion in database.
     * @param id {@link T} to delete.
     * @return deleted {@link T}
     * @throws ConstraintsViolationException if something goes wrong.
     * @throws EntityNotFoundException if the {@link T} with id does not exists in database.
     */
    T delete(Long id) throws EntityNotFoundException, ConstraintsViolationException;
}
