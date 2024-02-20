package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.ProductException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> viewAllProduct() throws ProductException {
        List<Product> products = productRepository.findAll();
        if (products.size() > 0) {
            return products;
        } else {
            throw new ProductException("Products not found");
        }
    }

    @Override
    public Product addProduct(Product product) throws ProductException {
        Product pro = productRepository.save(product);
        if (pro != null) {
            return pro;
        } else {
            throw new ProductException("Product not added");
        }

    }

    @Override
    public Product updateProduct(Product product) throws ProductException {
        Optional<Product> opt = productRepository.findById(product.getProductId());
        if (opt.isPresent()) {
            return productRepository.save(product);

        } else {
            throw new ProductException("Product not updated");
        }

    }

    @Override
    public Product viewProduct(Integer productId) throws ProductException {
        Optional<Product> opt = productRepository.findById(productId);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ProductException("Product not found with product id - " + productId);
        }
    }

    @Override
    public Product removeProduct(Integer productId) throws ProductException {
        Product p = productRepository.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
        productRepository.delete(p);
        return p;
    }
}
