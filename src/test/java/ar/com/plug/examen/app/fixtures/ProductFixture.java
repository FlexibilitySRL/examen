package ar.com.plug.examen.app.fixtures;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.model.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductFixture {

    public static ProductApi getProductApiWithNameAndDescription(String name, String description) {

        ProductApi productApi = ProductApi.builder()
                .name(name)
                .description(description)
                .price(100.0)
                .stock(10L)
                .build();
        return productApi;
    }

    public static ProductApi getProductApiWithId(Long id) {

        ProductApi productApi = ProductApi.builder()
                .id(id)
                .build();
        return productApi;
    }

    public static ProductApi getProductApi() {

        ProductApi productApi = ProductApi.builder()
                .name("Notebook")
                .description("Lenovo")
                .price(5000.0)
                .stock(10L)
                .build();
        return productApi;
    }

    public static List<ProductApi> getProducApitList(ProductApi productApi1, ProductApi productApi2) {

        List<ProductApi> lsProducts = new ArrayList<>();
        lsProducts.add(productApi1);
        lsProducts.add(productApi2);
        return lsProducts;
    }

    public static List<Product> getProductList(Product product1) {

        List<Product> listProducts = new ArrayList<>();
        listProducts.add(product1);
        return listProducts;
    }

    public static Product getProductWithId(Long id) {

        Product product = Product.builder()
                .id(id)
                .name("Notebook")
                .description("Lenovo")
                .price(2000.0)
                .stock(10L)
                .build();
        return product;
    }

    public static Product getProduct() {

        Product product = Product.builder()
                .name("Notebook")
                .description("Lenovo")
                .price(2000.0)
                .stock(10L)
                .build();
        return product;
    }
}
