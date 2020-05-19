package ar.com.flexibility.examen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1 -PENDING, 2 -APPROVED, 3 - CANCELELD, 4 -REJECTED
    private int status;
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    @ManyToOne
    private Seller seller;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public Order(Long id, Set<OrderProduct> orderProducts, Customer customer, Seller seller) {
        this.id = id;
        this.orderProducts = orderProducts;
        this.customer = customer;
        this.seller = seller;
    }


}
