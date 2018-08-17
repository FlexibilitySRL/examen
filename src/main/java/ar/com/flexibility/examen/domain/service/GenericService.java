package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.GenericEntity;

import java.util.List;

public interface GenericService<T extends GenericEntity> {

    T findOne(Long id) throws EntityNotFoundException;

    List<T> listAll();

    T create(T product) throws ConstraintsViolationException;

    T save(T product) throws EntityNotFoundException;

    T delete(Long id) throws EntityNotFoundException, ConstraintsViolationException;
}
