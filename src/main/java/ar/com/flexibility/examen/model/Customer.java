package ar.com.flexibility.examen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    @OneToMany(mappedBy="customer")

    private Set<Order> orders;

    public Customer(Long id, String name, String lastName, String email, String address, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.orders = orders;
    }
}
