package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.app.dto.ProductDto;
import ar.com.plug.examen.domain.model.Product;

public class ProductMapper {
    public static Product toProduct(ProductApi productApi) {
        return Product.builder()
                .sku(productApi.getSku())
                .name(productApi.getName())
                .description(productApi.getDescription())
                .price(productApi.getPrice())
                .build();
    }

    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product updateProduct(Product product, ProductApi productApi) {
        product.setSku(productApi.getSku());
        product.setName(productApi.getName());
        product.setDescription(productApi.getDescription());
        product.setPrice(productApi.getPrice());
        return product;
    }
}
