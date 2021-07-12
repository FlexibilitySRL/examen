package fixtures;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductFixture {

  public static ProductApi getProductApiWithNameAndDescription(String name, String description) {
    ProductApi productApi = new ProductApi();
    productApi.setName(name);
    productApi.setDescription(description);
    productApi.setPrice(100.0);
    productApi.setStock(10L);
    return productApi;
  }

  public static ProductApi getProductApiWithId(Long id) {
    ProductApi productApi = new ProductApi();
    productApi.setId(id);
    return productApi;
  }

  public static ProductApi getProductApi() {
    ProductApi productApi = new ProductApi();
    productApi.setName("Notebook");
    productApi.setDescription("Lenovo");
    productApi.setPrice(5000.0);
    productApi.setStock(10L);
    return productApi;
  }

  public static List<ProductApi> getProducApitList(ProductApi productApi1, ProductApi productApi2) {
    List<ProductApi> lsProducts = new ArrayList<ProductApi>();
    lsProducts.add(productApi1);
    lsProducts.add(productApi2);
    return lsProducts;
  }

  public static List<Product> getProductList(Product product1) {
    List<Product> lsProducts = new ArrayList<Product>();
    lsProducts.add(product1);
    return lsProducts;
  }

  public static Product getProductWithId(Long id) {
    Product product = new Product();
    product.setStock(10L);
    product.setPrice(200.0);
    product.setName("Notebook");
    product.setDescription("Lenovo");
    product.setId(id);
    return product;
  }

  public static Product getProduct() {
    Product product = new Product();
    product.setName("Notebook");
    product.setDescription("Lenovo");
    product.setPrice(5000.0);
    product.setStock(10L);
    return product;
  }

}
