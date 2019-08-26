package ar.com.flexibility.examen.domain.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) {this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }

    public void setStock(Integer stock) { this.stock = stock; }

    public Long getId() { return id; }

    public void setId(Long id)
    {
        this.id = id;
    }
}