package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.ProductApi;
import ar.com.flexibility.examen.domain.model.Product;

/**
 * Mapper to transform {@link ProductApi} to {@link Product} and vice versa.
 */
public class ProductApiMapper implements EntityMapper<ProductApi, Product> {

    /**
     * {@inheritDoc}
     */
    public ProductApi buildApi(Product product) {
        return ProductApi.newBuilder()
                .setId(product.getId())
                .setDateCreated(product.getDateCreated())
                .setName(product.getName())
                .setPrice(product.getPrice())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    public Product buildEntity(ProductApi productApi) {
        return new Product(
                productApi.getId(),
                productApi.getDateCreated(),
                productApi.getName(),
                productApi.getPrice());
    }
}