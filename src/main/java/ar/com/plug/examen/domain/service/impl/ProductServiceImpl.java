package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends  GenericServiceImpl<Product>{

    public ProductServiceImpl(ProductRepository productRepository){
        this.repository = productRepository;
    }
}
