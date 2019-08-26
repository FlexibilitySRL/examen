package ar.com.flexibility.examen.domain.service;


import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product create(Product product);
    public Product update(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
    public void deleteById(Long id);
}
