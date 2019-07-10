package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.exception.ProductExistsException;
import ar.com.flexibility.examen.domain.service.exception.ProductNotFoundException;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;
    Product createProduct(Product product) throws ProductExistsException;
    void updateProduct(Product product) throws ProductNotFoundException;
    void deleteProduct(Long id) throws  ProductNotFoundException;

}
