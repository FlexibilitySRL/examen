package ar.com.plug.examen.domain.dto;

import java.math.BigDecimal;

public class FacturaDetalleDTO {
	
	private long idFDetalle;
	private long idProducto;
	private int cantidad;
	private BigDecimal subtotal;
	
	public FacturaDetalleDTO() {
		
	}

	public FacturaDetalleDTO(long idFDetalle, long idProducto, int cantidad, BigDecimal subtotal) {
		this.idFDetalle = idFDetalle;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public long getIdFDetalle() {
		return idFDetalle;
	}

	public void setIdFDetalle(long idFDetalle) {
		this.idFDetalle = idFDetalle;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "FacturaDetalleDTO [idFDetalle=" + idFDetalle + ", idProducto=" + idProducto + ", cantidad=" + cantidad
				+ ", subtotal=" + subtotal + "]";
	}
}
