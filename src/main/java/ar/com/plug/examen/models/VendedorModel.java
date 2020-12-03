package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "vendedores")
public class VendedorModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String sector;
    private String documento;
    private Integer ventas;

    public VendedorModel(Long id, String nombre, String apellido, String telefono, String sector, String documento, Integer ventas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.sector = sector;
        this.documento = documento;
        this.ventas = ventas;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }
}