package ar.com.flexibility.examen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String description;
    private String model;
    private String brand;
    private int stock;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Product(Long productId,
                   String description,
                   Set<OrderProduct> orderProducts,
                   String model,
                   String brand,
                   int stock) {
        this.id = productId;
        this.description = description;
        this.model = model;
        this.brand = brand;
        this.stock = stock;
        this.orderProducts = orderProducts;
    }
}
