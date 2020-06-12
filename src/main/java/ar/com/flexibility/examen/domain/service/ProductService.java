package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

public interface ProductService {
    public Product create(Product product);
    public Product read(Long id);
    public Product update(Product product);
    public void delete(Long id);    
}
