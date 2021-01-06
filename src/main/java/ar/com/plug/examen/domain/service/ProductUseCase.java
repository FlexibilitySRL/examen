package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.CustomerApi;
import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.infrastructure.exception.CustomerEmailExistException;
import ar.com.plug.examen.infrastructure.exception.ProductDuplicateException;
import ar.com.plug.examen.infrastructure.exception.ResourceNotFoundException;

public interface ProductUseCase {

    ProductApi save(final ProductApi product) throws ProductDuplicateException;
    ProductApi update(final ProductApi product, final Long id) throws ResourceNotFoundException, ProductDuplicateException;
    Boolean delete(final Long id) throws ResourceNotFoundException;

}
