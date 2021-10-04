package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "compra")
@Data
public class Compra {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Producto producto;
    private int cantidad;
    private String estado;
    @ManyToOne
    private Transaccion transaccion;
}
