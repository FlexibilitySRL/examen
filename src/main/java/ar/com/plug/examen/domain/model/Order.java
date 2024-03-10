package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // Eager fetching recommended for client details
    private Customer client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Cascade for saving/deleting products
    private List<Product> products;

    private LocalDateTime orderDate; // Date and time of the order
    private BigDecimal total; // Total price of all products


    public BigDecimal calculateTotalPrice() {
        return products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

