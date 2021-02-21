package ar.com.plug.examen.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "vendedores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vendedor implements Serializable, Comparable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String Apellido;
    private String documento;
    private int numLegajo;

    public Vendedor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getNumLegajo() {
        return numLegajo;
    }

    public void setNumLegajo(int numLegajo) {
        this.numLegajo = numLegajo;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
