package ar.com.flexibility.examen.utils;

import ar.com.flexibility.examen.app.api.ProductDto;
import ar.com.flexibility.examen.domain.model.Product;

public class ProductBuilder extends Product {
    public static Product create(long id, String name, String description) {
        final Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        return product;
    }

    public static ProductDto createDto(long id, String name) {
        final ProductDto product = new ProductDto();
        product.setId(id);
        product.setName(name);
        return product;
    }
}
