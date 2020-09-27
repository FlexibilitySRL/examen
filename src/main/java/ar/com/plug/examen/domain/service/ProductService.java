package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.exception.ProductAlreadyExistsException;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;

public interface ProductService {

    Product createProduct(Product product);
    Product getProduct(String cod) throws ProductNotFoundException;
    Product updateProduct(Product product) throws ProductNotFoundException;
    void deleteProduct(String cod) throws  ProductNotFoundException;

}
