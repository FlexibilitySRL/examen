package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.exceptions.ProductDoesNotExistException;
import ar.com.plug.examen.domain.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product save(Product aProduct);
    Product findById(Long id) throws ProductDoesNotExistException;
    void delete(Product aProduct);
}
