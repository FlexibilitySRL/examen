package ar.com.plug.examen.domain.dto;

import javax.validation.constraints.NotBlank;

public class ProductoAltaDto {

	private String nombre;

	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
