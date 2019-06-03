package ar.com.flexibility.examen.domain.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Producto {
	@Id
	@Column(name="id")
	private int idProducto;
	@Column(name="nombre")
	private String nombre;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="precio")
	private float precio;
	@OneToMany(targetEntity = Ventas.class, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<Ventas> venta;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
