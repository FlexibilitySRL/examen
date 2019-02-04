package ar.com.flexibility.examen.domain.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Producto")
public class Producto {

	@Id
	@Column(name="id_producto")
	private int idProducto;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="fechaVencimiento")
	private Long fechaVencimiento;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Long getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Long fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	private Set<Renglon> renglon = new HashSet<Renglon>(0);

	public Set<Renglon> getRenglon() {
		return renglon;
	}

	public void setRenglon(Set<Renglon> renglon) {
		this.renglon = renglon;
	}
	

}
