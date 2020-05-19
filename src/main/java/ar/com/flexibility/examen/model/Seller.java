package ar.com.flexibility.examen.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({ "id", "orders" })
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String address;
    String email;
    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

    public Seller(Long id, String name, String address, String email, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.orders = orders;
    }
}
