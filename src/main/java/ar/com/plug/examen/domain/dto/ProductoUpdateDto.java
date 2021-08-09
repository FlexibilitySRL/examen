package ar.com.plug.examen.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductoUpdateDto {

	@NotNull
	private Integer id;
	
	private String nombre;
	
	@Min(value = 0)
	private Integer precioUnitario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Integer precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
}
