package ar.com.flexibility.examen.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String name;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }
}
