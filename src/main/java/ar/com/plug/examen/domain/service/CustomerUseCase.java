package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.infrastructure.exception.CustomerEmailExistException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;

public interface CustomerUseCase {

    CustomerApi save(final CustomerApi customer) throws CustomerEmailExistException;
    CustomerApi update(final CustomerApi customer, final Long id) throws CustomerEmailExistException, ResourceNotFoundException;
    Boolean delete(final Long id) throws ResourceNotFoundException;

}
