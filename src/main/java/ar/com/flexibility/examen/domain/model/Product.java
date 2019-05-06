package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.app.api.ProductApi;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    public Product(){}

    public Product(ProductApi productApi){
        this.id = productApi.getId();
        this.description = productApi.getDescription();
        this.price = productApi.getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        Product p = (Product) obj;

        if(!Objects.equals(this.id, p.getId()))
            return false;

        if(!Objects.equals(this.description, p.getDescription()))
            return false;

        if(!Objects.equals(this.price, p.getPrice()))
            return false;

        return true;
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
