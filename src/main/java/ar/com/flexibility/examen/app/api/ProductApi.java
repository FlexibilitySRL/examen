package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

@JsonRootName(value = "product")
public class ProductApi {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private Integer stock;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Product toEntity()
    {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        return product;
    }

    public static ProductApi toApi(Product product) {
        final ProductApi productApi = new ProductApi();
        if(product != null) {
            productApi.setId(product.getId());
            productApi.setName(product.getName());
            productApi.setDescription(product.getDescription());
        }
        return productApi;
    }

    public Long getId() { return id;  }

    public String getName() { return name;}

    public String getDescription() { return description; }

    public BigDecimal getPrice() { return price; }

    public Integer getStock() { return stock; }
}
