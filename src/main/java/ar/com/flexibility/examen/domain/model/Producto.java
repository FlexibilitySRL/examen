package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id
	@Column (name = "id")
	private int id;
	
	@Column (name = "producto")
	private String producto;
	
	@Column (name = "precio")
	private int precio;
	
	
	public Producto(int id, String producto, int precio) {
		
		this.id = id;
		this.producto = producto;
		this.precio = precio;
	}

	
	public Producto () {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	

}
