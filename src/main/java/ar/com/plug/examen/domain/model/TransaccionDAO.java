package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaccion")
public class TransaccionDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTransaccion")
    private Long idTransaccion;

    @Enumerated(EnumType.STRING)
    private EstadoTransaccion estadoTransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    @JsonIgnoreProperties({"listaTransacciones", "hibernateLazyInitializer", "handler"})
    private ClienteDAO cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVendedor")
    @JsonIgnoreProperties({"listaTransacciones", "hibernateLazyInitializer", "handler"})
    private VendedorDAO vendedor;

    @Column(name = "precioTotal", nullable = false)
    private float precioTotal;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaCreacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date fechaModificacion;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "transactionproducto",
            joinColumns = @JoinColumn(name = "idTransaccion"),
            inverseJoinColumns = @JoinColumn(name = "idProducto")
    )
    @JsonIgnoreProperties("listaTransacciones")
    private List<ProductoDAO> listaProductos;


    public TransaccionDAO() {
    }

    public TransaccionDAO(EstadoTransaccion estadoTransaccion, ClienteDAO cliente, float precioTotal, Date fechaCreacion, List<ProductoDAO> listaProductos) {
        this.estadoTransaccion = estadoTransaccion;
        this.cliente = cliente;
        this.precioTotal = precioTotal;
        this.fechaCreacion = fechaCreacion;
        this.listaProductos = listaProductos;
    }

    public TransaccionDAO(Long idTransaccion, EstadoTransaccion estadoTransaccion, ClienteDAO cliente, VendedorDAO vendedor, float precioTotal, Date fechaCreacion, Date fechaModificacion, List<ProductoDAO> listaProductos) {
        this.idTransaccion = idTransaccion;
        this.estadoTransaccion = estadoTransaccion;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.precioTotal = precioTotal;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.listaProductos = listaProductos;
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public EstadoTransaccion getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEstadoTransaccion(EstadoTransaccion estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public ClienteDAO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDAO cliente) {
        this.cliente = cliente;
    }

    public VendedorDAO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDAO vendedor) {
        this.vendedor = vendedor;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<ProductoDAO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDAO> listaProductos) {
        this.listaProductos = listaProductos;
    }
}
