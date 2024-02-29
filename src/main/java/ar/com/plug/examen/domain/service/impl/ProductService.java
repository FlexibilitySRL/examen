package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.NotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public Product getById(Long id){
        LOGGER.info("ProductService.getById() id :{}",id);
        return repository.findById(id).orElseThrow( () -> new NotFoundException("Entity with id "+id+" not found"));
    }

    public Product saveOrUpdate(Product product) {
        LOGGER.info("ProductService.saveOrUpdate() product :{}",product);
        return this.repository.save(product);
    }

    public void delete(Long id) {
        LOGGER.info("ProductService.delete() id :{}",id);
        Product product = this.getById(id);
        this.repository.delete(product);
    }

    public List<Product> getAll() {
        LOGGER.info("ProductService.getAll()");
        return this.repository.findAll();
    }

}
