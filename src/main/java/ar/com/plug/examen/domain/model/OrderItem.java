package ar.com.plug.examen.domain.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private Double price;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return "OrderItem{" +
                "sku='" + sku + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}