package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.exception.ProductAlreadyExistException;
import ar.com.plug.examen.domain.exception.ProductNotFoundException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        Optional<Product> foundProduct = productRepository.findByDescription(product.getDescription());
        if (foundProduct.isPresent()) {
            throw new ProductAlreadyExistException();
        }
        return productRepository.save(product);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
