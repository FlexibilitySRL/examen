package ar.com.plug.examen.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "vendedores")
public class VendedorModel extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private String sector;
    private String documento;
    private Integer ventas;

    public VendedorModel(Long id, String nombre, String apellido, String telefono, String sector, String documento, Integer ventas) {
        super(id, nombre, apellido, telefono);
        this.sector = sector;
        this.documento = documento;
        this.ventas = ventas;
    }

    //getters y setters
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