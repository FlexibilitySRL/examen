package ar.com.plug.examen.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.repository.ProductRepository;
import ar.com.plug.examen.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        log.info("::createProduct:: {}", product.getName());
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        log.info("::getProductById:: {}", id);
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
