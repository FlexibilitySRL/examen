package ar.com.plug.examen.domain.service;

import org.springframework.data.domain.Page;

import ar.com.plug.examen.app.exception.BadResourceException;
import ar.com.plug.examen.app.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.model.Product;

public interface ProductService {

	Product findById(Long id) throws ResourceNotFoundException;

	Page<Product> findAll(int pageNumber, int rowPerPage);

	Product save(Product product) throws BadResourceException;

	Product update(Product product) throws ResourceNotFoundException;

	void deleteById(Long id) throws ResourceNotFoundException, BadResourceException;

}