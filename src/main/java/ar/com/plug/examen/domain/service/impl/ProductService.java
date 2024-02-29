package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GenericService<Product>{

    private final ProductRepository repository;

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    @Override
    public ProductRepository getRepository() {
        return repository;
    }
}
