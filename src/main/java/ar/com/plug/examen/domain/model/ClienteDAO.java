package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCliente")
    private Long idCliente;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @OneToMany(
            mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties("cliente")
    private List<TransaccionDAO> listaTransacciones;

    @Enumerated(EnumType.STRING)
    private EstadoLogico estadoLogico;


    public ClienteDAO() {
    }
    public ClienteDAO(String dni, String nombre, String apellido, String email, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    public ClienteDAO(String dni, String nombre, String apellido, String email, String telefono, EstadoLogico estadoLogico) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.estadoLogico = estadoLogico;
    }
    public ClienteDAO(Long idCliente, String dni, String nombre, String apellido, String email, String telefono, List<TransaccionDAO> listaTransacciones, EstadoLogico estadoLogico) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.listaTransacciones = listaTransacciones;
        this.estadoLogico = estadoLogico;
    }

    public Long getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public List<TransaccionDAO> getListaTransacciones() {
        return listaTransacciones;
    }
    public void setListaTransacciones(List<TransaccionDAO> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }
    public EstadoLogico getEstadoLogico() {
        return estadoLogico;
    }
    public void setEstadoLogico(EstadoLogico estadoLogico) {
        this.estadoLogico = estadoLogico;
    }
}
