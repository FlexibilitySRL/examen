package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	@Id
	@Column(name="id")
	private int idCliente;
	@Column(name="nombre")
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdProducto() {
		return idCliente;
	}
	public void setIdProducto(int idProducto) {
		this.idCliente = idProducto;
	}
}
