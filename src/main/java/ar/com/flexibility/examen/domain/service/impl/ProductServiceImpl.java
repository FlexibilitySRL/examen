package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.repo.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    ProductRepository repo;
    
    @Override
    public Product create(Product product) {
        LOGGER.info("Saving product");
        return repo.save(product);
    }

    @Override
    public Product read(Long id) {
        LOGGER.info("reading product");
        return repo.findOne(id);
    }

    @Override
    public Product update(Product product) {
        LOGGER.info("updating product");
        return repo.save(product);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("deleting product");
        repo.delete(id);
    }
}
