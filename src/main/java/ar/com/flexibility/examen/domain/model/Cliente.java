package ar.com.flexibility.examen.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.com.flexibility.examen.domain.model.Compra;

public class Cliente {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	private long id;
	private String nombre;
	private String tarjeta;
	private List<Compra> compras = new ArrayList<Compra>();
	
	public Cliente(long id, String nombre, String tarjeta) {
		this.id = id;
		this.nombre = nombre;
		this.tarjeta = tarjeta;
		
		compras.add(new Compra(new Producto(1, "prod 1"),DateTime.now(), 100));
	}
	
	public Cliente(String nombre, String tarjeta) {
		this.nombre = nombre;
		this.tarjeta = tarjeta;
	}

	public List<Compra> compras() {
		return compras;
	}
	
}
