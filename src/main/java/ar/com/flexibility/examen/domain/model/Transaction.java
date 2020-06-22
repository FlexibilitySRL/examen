package ar.com.flexibility.examen.domain.model;

import ar.com.flexibility.examen.app.api.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany
    private List<Product> product;
    @OneToOne
    private Vendor vendor;
    @OneToOne
    private Client client;
    private Double price;
    private Status status;

}
