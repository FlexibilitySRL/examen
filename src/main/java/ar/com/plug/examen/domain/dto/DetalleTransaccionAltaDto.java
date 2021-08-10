package ar.com.plug.examen.domain.dto;

import javax.validation.constraints.NotNull;

public class DetalleTransaccionAltaDto {

	@NotNull
	private Integer idTransaccion;

	@NotNull
	private Integer idProducto;
	
	@NotNull
	private Integer cantidad;

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
