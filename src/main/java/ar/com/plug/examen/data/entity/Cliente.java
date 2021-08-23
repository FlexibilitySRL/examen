package ar.com.plug.examen.data.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente {
	
	@Id
	@Column(name="ID_CLIENTE")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente; 
	
	@Column(name = "NUM_IDENT")
	private String numIdent;
	
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "FECHA_A")
	private Timestamp fechaA;
	
	@Column(name = "FECHA_M")
	private Timestamp fechaM;
	
	public Cliente(){
		
	}

	public Cliente(long idCliente, String numIdent, String nombre, String apellido, String direccion, String email,
			String telefono, Timestamp fechaA, Timestamp fechaM) {
		this.idCliente = idCliente;
		this.numIdent = numIdent;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.fechaA = fechaA;
		this.fechaM = fechaM;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNumIdent() {
		return numIdent;
	}

	public void setNumIdent(String numIdent) {
		this.numIdent = numIdent;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public Timestamp getFechaA() {
		return fechaA;
	}

	public void setFechaA(Timestamp fechaA) {
		this.fechaA = fechaA;
	}

	public Timestamp getFechaM() {
		return fechaM;
	}

	public void setFechaM(Timestamp fechaM) {
		this.fechaM = fechaM;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", numIdent=" + numIdent + ", nombre=" + nombre + ", apellido="
				+ apellido + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", fechaA="
				+ fechaA + ", fechaM=" + fechaM + "]";
	}	
	
	
}

