package ar.com.flexibility.examen.app.api;

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
    private double price;

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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getId() { return id;  }

    public String getName() { return name;}

    public String getDescription() { return description; }

    public double getPrice() { return price; }

    public Integer getStock() { return stock; }
}
