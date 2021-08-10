package ar.com.plug.examen.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {
	
	private Integer id;

	private Integer documento;

	private String apellido;
	
	private String nombre;

	private String direccion;
		
	private Date fechaNacimiento;
		
	private String telefono;
	
	

	public Cliente() {
		super();
	}

	public Cliente(Integer documento, String apellido, String nombre, String direccion, Date fechaNacimiento,
			String telefono) {
		super();
		this.documento = documento;
		this.apellido = apellido;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return apellido;
	}

	@Column(name = "direccion", nullable = false)
	public String getDireccion() {
		return direccion;
	}

	@Column(name = "documento", nullable = false, unique = true)
	public Integer getDocumento() {
		return documento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaNacimiento", nullable = false)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return nombre;
	}

	@Column(name = "telefono", nullable = false)
	public String getTelefono() {
		return telefono;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
