package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

@JsonRootName(value = "Product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApi {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String description;

    @JsonProperty
    private BigDecimal price;


    public ProductApi(){}

    public ProductApi(Product product){
        this.id = product.getId();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
