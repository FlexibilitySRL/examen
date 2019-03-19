package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product create(Product product) {
        product.setDescription("Descripcion generica");
        return repository.save(product);
    }

    @Override
    public Product findById(long id) {
        return repository.findOne(id);
    }

    @Override
    public Boolean delete(long id) {
        try {
            repository.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
