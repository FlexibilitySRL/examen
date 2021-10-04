package ar.com.plug.examen.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transaccion")
@Data
public class Transaccion {
    @Id
    @GeneratedValue
    private Long id;
    private String estado;
    @ManyToOne
    private Cliente cliente;
    private Long totalTransaccion;
}
