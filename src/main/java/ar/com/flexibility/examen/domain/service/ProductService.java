package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);

    List<Product> retrieveProducts();
    Product retrieveProductById(Long id);

}
