package ar.com.flexibility.examen.domain.model;



public class Producto {

	private String nombre;
	private long id;
	
	public Producto(String nombre) {
		this.setNombre(nombre);
	}
	
	public Producto(long id, String nombre) {
		this.setId(id);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	
}
