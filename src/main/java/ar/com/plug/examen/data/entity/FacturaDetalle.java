package ar.com.plug.examen.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FACTURA_DETALLE")
public class FacturaDetalle {

	@Id
	@Column(name="ID_FDETALLE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFDetalle; 

	@Column(name="ID_PRODUCTO")
	private long idProducto; 

	@Column(name = "CANTIDAD")
	private int cantidad;

	@Column(name = "SUBTOTAL")
	private BigDecimal subtotal;

	@ManyToOne
	@JsonIgnoreProperties("facturaDetalle")
  	private Factura factura;

	public FacturaDetalle() {

	}
	
	public FacturaDetalle(long idFDetalle, long idProducto, int cantidad, BigDecimal subtotal, Factura factura) {
		this.idFDetalle = idFDetalle;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.factura = factura;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
