package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "productos")

public class ProductoModel {

    //atributos
    private Long id;
    @ManyToOne
    private ClienteModel categoria;
    private String nombre;
    private Integer stock;
    private Double precio;

    //constructor
    public ProductoModel(Long id, Integer categoria, String nombre, Integer stock, Double precio) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
    
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio(){
        return precio;
    }

    public void setPrecio(Double precio){
        this.precio = precio;
    }
}