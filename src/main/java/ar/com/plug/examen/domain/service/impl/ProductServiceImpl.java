package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import ar.com.plug.examen.domain.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (!productRepository.existsById(product.getCod()))
            throw new ProductNotFoundException();

        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String cod) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(cod);

        if (!product.isPresent())
            throw new ProductNotFoundException();

        return product.get();
    }

    @Override
    public boolean exists(String cod) {
        return productRepository.existsById(cod);
    }

    @Override
    public void deleteProduct(String cod) throws ProductNotFoundException {
        try {
            productRepository.deleteById(cod);
        }catch (EmptyResultDataAccessException ex) {
            throw new ProductNotFoundException();
        }
    }

}
