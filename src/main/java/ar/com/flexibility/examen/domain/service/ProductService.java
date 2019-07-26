package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Product product);

    Product findById(Long id);

    void deleteProduct(Long product);

    List<Product> findAll();
}
