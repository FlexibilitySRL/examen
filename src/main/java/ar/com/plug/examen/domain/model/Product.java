package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name may not be null")
    private String name;

    @Column(name = "brand")
    @NotNull(message = "Brand may not be null")
    private String brand;

    @Column(name = "description")
    @NotNull(message = "Description may not be null")
    private String description;

    @Column(name = "price")
    @NotNull(message = "price may not be null")
    private BigDecimal price;

    @Column(name = "active")
    private Boolean active = true;

    public Product() {
    }

    public Product(String name, String brand, String description, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String brand, String description, BigDecimal price, Boolean active) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
