package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ProductDTO;

public interface ProductService {
    void createProduct(ProductDTO productDTO);

    void deleteProduct(Long idProduct);

    void editProduct(Long idProduct, ProductDTO productDTO);
}
