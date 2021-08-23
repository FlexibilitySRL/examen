package ar.com.plug.examen.data.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="VENDEDOR")
public class Vendedor {
	
	@Id
	@Column(name="ID_VENDEDOR")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVendedor; 
	
	@Column(name = "NUM_IDENT")
	private String numIdent;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "FECHA_A")
	private Timestamp fechaA;
	
	@Column(name = "FECHA_M")
	private Timestamp fechaM;
	
	public Vendedor() {
		
	}

	public Vendedor(long idVendedor, String numIdent, String codigo, String nombre, String apellido, String email,
			String telefono, Timestamp fechaA, Timestamp fechaM) {
		this.idVendedor = idVendedor;
		this.numIdent = numIdent;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaA = fechaA;
		this.fechaM = fechaM;
	}

	public long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getNumIdent() {
		return numIdent;
	}

	public void setNumIdent(String numIdent) {
		this.numIdent = numIdent;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return "Vendedor [idVendedor=" + idVendedor + ", numIdent=" + numIdent + ", codigo=" + codigo + ", nombre="
				+ nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", fechaA="
				+ fechaA + ", fechaM=" + fechaM + "]";
	}
}
