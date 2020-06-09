package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    void delete(Long id);
    Optional<Product> findAll();
}
