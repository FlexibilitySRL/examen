package ar.com.plug.examen.model;

import javax.persistence.*;

@Entity
@Table(name = "categorias")

public class CategoriaModel {

    //atributos
    private Long id;
    private String nombre;
    private Boolean estado;

    //constructor
    public CategoriaModel(Long id, String nombre, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    //getters y setters
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

    public Boolean getEstado(){
        return estado;
    }

    public void setEstado(Boolean estado){
        this.estado = estado;
    }
}