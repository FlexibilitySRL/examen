package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;

public class ProductApiMapper {

    public static ProductApi buildProductApi(Product product) {
        return ProductApi.newBuilder()
                .setId(product.getId())
                .setDateCreated(product.getDateCreated())
                .setName(product.getName())
                .build();
    }

    public static Product buildProduct(ProductApi productApi) {
        return new Product(productApi.getId(), productApi.getDateCreated(), productApi.getName());
    }
}