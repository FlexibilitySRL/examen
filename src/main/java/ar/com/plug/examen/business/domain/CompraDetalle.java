package ar.com.plug.examen.business.domain;

public class CompraDetalle {
	
	private long idProducto;

	private int cantidad;
	private double precio;
	private double subtotal;
		
	public CompraDetalle(long idProducto, long nombreProducto, int cantidad, double precio, double subtotal) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = cantidad*precio;
	}

	public CompraDetalle() {
		
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}	
}
