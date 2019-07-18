package ar.com.flexibility.examen.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "CLIENTE")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "CONTACTO")
    private String contanto;
   
    @OneToMany(targetEntity = Compra.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "cliente")
    private List<Compra> compras;
    
    
	public Cliente(int id, String nombre, String apellido, String contanto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contanto = contanto;

    }

    public Cliente() {
    }
    
    

    public Cliente(Cliente cliente) {
		this.id = cliente.getId();
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.contanto = cliente.getContanto();

	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getContanto() {
        return contanto;
    }

    public void setContanto(String contanto) {
        this.contanto = contanto;
    }

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}


}
