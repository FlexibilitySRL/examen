package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductApi productToProductApi(Product product) {
    ProductApi productApi = new ProductApi();
    productApi.setId(product.getId());
    productApi.setName(product.getName());
    productApi.setDescription(product.getDescription());
    productApi.setPrice(product.getPrice());
    productApi.setStock(product.getStock());
    return productApi;
  }

  public Product productApiToProduct(ProductApi productApi) {
    Product product = new Product();
    product.setId(productApi.getId());
    product.setName(productApi.getName());
    product.setDescription(productApi.getDescription());
    product.setPrice(productApi.getPrice());
    product.setStock(productApi.getStock());
    return product;
  }

  public List<ProductApi> productsToListProductApi(List<Product> products) {
    return products.stream().map(this::productToProductApi).collect(Collectors.toList());
  }

}
