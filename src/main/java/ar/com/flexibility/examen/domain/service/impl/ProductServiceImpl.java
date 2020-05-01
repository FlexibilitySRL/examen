package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import ar.com.flexibility.examen.domain.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> retrieveProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product retrieveProductById(Long id) {
        Product product = productRepository.findOne(id);

        if (product == null) {
            logger.trace(String.format("Could not retrieve product with id %s", id));
        }

        return product;
    }

    @Override
    public Product addProduct(Product product) {
        Product newProduct = productRepository.save(product);

        if (newProduct == null) {
            logger.trace(String.format("Could not create the product %s", product.getName()));
        }

        return newProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.exists(id))
        {
            logger.trace(String.format("Could not update the product with id %s. It does not exist.", product.getId()));
            return null;
        }

        product.setId(id);
        Product updatedProduct = productRepository.save(product);

        if (updatedProduct == null) {
            logger.trace(String.format("Could not update the product with id %s", product.getId()));
        }

        return updatedProduct;
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (!productRepository.exists(id)) {
            logger.trace(String.format("Could not delete the product with id %s. It does not exist.", id));
            return false;
        }

        try {
            productRepository.delete(id);
        } catch (Exception e) {
            logger.trace(String.format("Could not delete the product with id %s. An internal error occurred: %s.",
                    id, e.getMessage()));
            return false;
        }

        return true;
    }
}
