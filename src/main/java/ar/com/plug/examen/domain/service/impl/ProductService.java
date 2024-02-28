package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public Product getById(Long id){
        return repository.findById(id).get();
    }

    public Product saveOrUpdate(Product product) {
        return this.repository.save(product);
    }

    public void delete(Long id) {
        Product product = this.getById(id);
        this.repository.delete(product);
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

}
