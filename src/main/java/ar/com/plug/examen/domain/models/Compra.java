package ar.com.plug.examen.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "compras")
public class Compra implements Serializable, Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name="clientes", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Cliente clienteId;
    @JoinColumn(name="vendedores", referencedColumnName = "id")
    @ManyToOne (cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private Vendedor vendedorId;

    @JoinColumn(name="productos", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    private List<Producto> productos;

    private Double precioSinIva;
    private Double impuestos;
    private String medioDePago;

    public Compra() {
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return clienteId;
    }

    public void setCliente(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Vendedor getVendedor() {
        return vendedorId;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedorId = vendedorId;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(Double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
