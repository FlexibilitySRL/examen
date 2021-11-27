package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    public ProductApi productToProductApi(Product product) {

        ProductApi productApi = ProductApi
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        return productApi;
    }

    public Product productApiToProduct(ProductApi productApi) {

        Product product = Product
                .builder()
                .id(productApi.getId())
                .name(productApi.getName())
                .description(productApi.getDescription())
                .price(productApi.getPrice())
                .stock(productApi.getStock())
                .build();

        return product;
    }

    public List<ProductApi> productsToListProductApi(List<Product> products) {
        return products.stream().map(this::productToProductApi).collect(Collectors.toList());
    }
}
