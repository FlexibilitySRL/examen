package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendedor")
public class VendedorDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idVendedor")
    private Long idVendedor;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Enumerated(EnumType.STRING)
    private EstadoLogico estadoLogico;

    @OneToMany(
            mappedBy = "vendedor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<TransaccionDAO> listaTransacciones;

    public VendedorDAO() {
    }

    public VendedorDAO(Long idVendedor, String nombre, String apellido) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public VendedorDAO(Long idVendedor, String nombre, String apellido, EstadoLogico estadoLogico, List<TransaccionDAO> listaTransacciones) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoLogico = estadoLogico;
        this.listaTransacciones = listaTransacciones;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
