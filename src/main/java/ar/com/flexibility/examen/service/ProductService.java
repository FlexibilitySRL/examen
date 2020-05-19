package ar.com.flexibility.examen.service;

import ar.com.flexibility.examen.app.dto.ProductDTO;
import ar.com.flexibility.examen.model.Product;

public interface ProductService {

    void createProduct(ProductDTO productDTO);
    Product retrieveProductById(Long id);
    void updateProduct(Long id, ProductDTO productDTO);
    void deleteProductById(Long id);
}
