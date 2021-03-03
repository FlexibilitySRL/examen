package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producto")
public class ProductoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idProducto")
    private Long idProducto;

    private String descripcion;

    private float precio;

    @Enumerated(EnumType.STRING)
    private EstadoLogico estadoLogico;

    @ManyToMany(mappedBy = "listaProductos")
    private List<TransaccionDAO> listaTransacciones;


    public ProductoDAO() {
    }
    public ProductoDAO(String descripcion, float precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public ProductoDAO(Long idProducto, String descripcion, float precio, EstadoLogico estadoLogico, List<TransaccionDAO> listaTransacciones) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estadoLogico = estadoLogico;
        this.listaTransacciones = listaTransacciones;
    }


    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public EstadoLogico getEstadoLogico() {
        return estadoLogico;
    }
    public void setEstadoLogico(EstadoLogico estadoLogico) {
        this.estadoLogico = estadoLogico;
    }
    public List<TransaccionDAO> getListaTransacciones() {
        return listaTransacciones;
    }
    public void setListaTransacciones(List<TransaccionDAO> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
}
