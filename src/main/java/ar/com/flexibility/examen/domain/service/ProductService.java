package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {

    Product create(Product product);

    Product findById(long id);

    Boolean delete(long id);
}
