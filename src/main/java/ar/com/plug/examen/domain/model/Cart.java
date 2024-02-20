package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    private Integer productQuantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
