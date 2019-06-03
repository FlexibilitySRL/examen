package ar.com.flexibility.examen.domain.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Cliente {
	@Id
	@Column(name="id")
	private int idCliente;
	@Column(name="nombre")
	private String nombre;
	@JoinColumn(name="ventas")
	@OneToMany(targetEntity = Cliente.class, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<Ventas> venta;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int id) {
		this.idCliente = id;
	}
}
