package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.exception.ConstraintsViolationException;
import ar.com.flexibility.examen.app.exception.EntityNotFoundException;
import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product findOne(Long id) throws EntityNotFoundException;

    List<Product> listAll();

    Product create(Product product) throws ConstraintsViolationException;

    Product save(Product product) throws EntityNotFoundException;

    Product delete(Long id) throws EntityNotFoundException, ConstraintsViolationException;
}