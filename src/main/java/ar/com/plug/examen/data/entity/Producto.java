package ar.com.plug.examen.data.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PRODUCTO")
public class Producto {

	@Id
	@Column(name="ID_PRODUCTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProducto; 
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "PRECIO")
	private Double precio;
	
	@Column(name = "FECHA_A")
	private Timestamp fechaA;
	
	@Column(name = "FECHA_M")
	private Timestamp fechaM;

	public Producto() {

	}

	public Producto(long idProducto, String codigo, String descripcion, int cantidad, Double precio, Timestamp fechaA,Timestamp fechaM) {
		this.idProducto = idProducto;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fechaA = fechaA;
		this.fechaM = fechaM;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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
		return "Producto [idProducto=" + idProducto + ", codigo=" + codigo + ", descripcion=" + descripcion
				+ ", cantidad=" + cantidad + ", precio=" + precio + ", fechaA=" + fechaA + ", fechaM=" + fechaM + "]";
	}
	
	
	
}
