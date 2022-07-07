package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "precio_total")
    private Double precioTotal;

    private String status;

    @ManyToOne
    private Persona persona;

    @ManyToMany
    List<Producto> productos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Persona getCliente() {
        return persona;
    }

    public void setCliente(Persona persona) {
        this.persona = persona;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
