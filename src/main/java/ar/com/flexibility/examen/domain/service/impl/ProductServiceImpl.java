package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(Long id){
        return productRepository.findOne(id);
    }

    @Override
    public Boolean exists(Long id){
        return productRepository.exists(id);
    }

    @Override
    public Product add(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product update(Product product) {

        Product productToPersist = productRepository.getOne(product.getId());

        productToPersist.setDescription(product.getDescription());
        productToPersist.setPrice(product.getPrice());

        return productRepository.saveAndFlush(productToPersist);
    }

    @Override
    public Boolean delete(Long id) {
        productRepository.delete(id);
        return !exists(id);
    }

}
