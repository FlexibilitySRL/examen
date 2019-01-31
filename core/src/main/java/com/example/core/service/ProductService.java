package com.example.core.service;

import com.example.core.model.Product;
import com.example.core.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        product.setActive(true);
        product.setDepositDate(new Date());
        productRepository.save(product);
        LOGGER.info("Se creo un nuevo producto");
        return product;
    }

    /***
     * Se realizan bajas logicas
     * @param id
     */
    public void delete(Long id) {
        LOGGER.info("Se elimino producto del catalogo.");
        productRepository.update(id, Boolean.FALSE);
    }

    public Product update(Product product) {
        productRepository.save(product);
        LOGGER.info("Se actualizan datos del producto {}.", product.getId());
        return product;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }
}
