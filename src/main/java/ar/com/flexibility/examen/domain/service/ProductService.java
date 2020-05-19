package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.exception.GenericException;

public interface ProductService {

	public Product create(Product product) throws GenericException;

	public Product getById(Long id) throws GenericException;

	public List<Product> getAll();

	public Product update(Product product) throws GenericException;

	public void delete(Long id) throws GenericException;

}