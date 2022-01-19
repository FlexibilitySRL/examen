package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "product_sold")
public class ProductSold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float cantidad, precio;
    private String nombre, codigo;
    @ManyToOne
    @JoinColumn
    private Purchase purchase;

    public ProductSold ProductoVendido(Float cantidad, Float precio, String nombre, String codigo, Purchase compra) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;
        this.codigo = codigo;
        this.purchase = compra;
        return this;
    }

    public ProductSold ProductoVendido() {
        return this;
    }

    public Float getTotal() {
        return this.cantidad * this.precio;
    }

    public Purchase getcompra() {
        return purchase;
    }

    public void setcompra(Purchase compra) {
        this.purchase = compra;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
