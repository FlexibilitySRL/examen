package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.repo.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;
    
    @Override
    public Product create(Product product) {
        return repo.save(product);
    }

    @Override
    public Product read(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Product update(Product product) {
        return repo.save(product);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
