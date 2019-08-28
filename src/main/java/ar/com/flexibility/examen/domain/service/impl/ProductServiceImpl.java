package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public Product create(Product product) {
        log.debug("Grabando : {}", product);
        return repo.save(product);
    }

    @Override
    public Product update(Product product) {
        log.debug("Actualizando : {}", product);
        
        Product productToUpdate = repo.findOne(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());

        return repo.save(productToUpdate);
    }

    @Override
    public Product findById(Long id) {
        log.debug("Buscando producto con id: {}", id);
        return repo.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        log.debug("Obteniendo todos los productos ");
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Borrando producto: {}", id);
        repo.delete(id);
    }
}
