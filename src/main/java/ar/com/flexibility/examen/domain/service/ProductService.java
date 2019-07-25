package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Product product);

    Product findById(Product product);

    void deleteProduct(Product product);
}
