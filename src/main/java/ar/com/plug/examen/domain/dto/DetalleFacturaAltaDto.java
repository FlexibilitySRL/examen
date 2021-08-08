package ar.com.plug.examen.domain.dto;

import javax.validation.constraints.NotNull;

public class DetalleFacturaAltaDto {

	@NotNull
	private Integer idFactura;

	@NotNull
	private Integer idProducto;
	
	@NotNull
	private Integer cantidad;

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
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
