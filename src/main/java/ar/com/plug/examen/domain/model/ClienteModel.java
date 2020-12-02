package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class ClienteModel extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private String ciudad;
    private String direccion;
    private Integer edad;

    public ClienteModel(Long id, String nombre, String apellido, String telefono, String ciudad, String direccion, Integer edad) {
        super(id, nombre, apellido, telefono);
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.edad = edad;
    }

    //getters y setters
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}