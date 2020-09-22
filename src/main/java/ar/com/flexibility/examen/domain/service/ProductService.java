package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.domain.dto.ProductDTO;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO product);
    
    public ProductDTO updateProduct(ProductDTO product);
    
    public ProductDTO deleteProduct(Long id);
    
    public ProductDTO getProduct(Long id);
}
