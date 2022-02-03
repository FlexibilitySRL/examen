package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl  implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Product> productDB = Optional.ofNullable(productRepository.findByCode(product.getCode()));
        if(productDB.isPresent()){
            return productDB.get();
        }
        product.setStatus("CREATED");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDB = Optional.ofNullable(getProduct(product.getId()));
        if (productDB.isEmpty()){
            return null;
        }
        Product productUpdated = productDB.get();
        productUpdated.setName(product.getName());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setPrice(product.getPrice());
        return productRepository.save(productUpdated);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> productDB = Optional.ofNullable(getProduct(id));
        if (productDB.isEmpty()){
            return null;
        }
        productDB.get().setStatus("DELETED");
        return productRepository.save(productDB.get());
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Optional<Product> productDB = Optional.ofNullable(getProduct(id));
        if (productDB.isEmpty()){
            return null;
        }
        Product productUpdated = productDB.get();
        Double stock =  productUpdated.getStock() + quantity;
        productUpdated.setStock(stock);
        return productRepository.save(productUpdated);
    }
}
