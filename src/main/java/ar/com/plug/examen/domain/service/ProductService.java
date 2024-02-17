package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductApi product);

    List<ProductDto> findAll();

    ProductDto findProductById(Long id);

    ProductDto updateProduct(Long id, ProductApi product);

    ProductDto deleteProduct(Long id);

    boolean isSkuValid(String sku);

    boolean areSkusValid(List<String> skus);
}
