package ar.com.plug.examen.domain.model;

import java.util.List;

import javax.persistence.*;
    
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(unique = true, nullable = false)
    private Long dni;

    private String nombre;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Purchase> Purchase;

    public Long getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
