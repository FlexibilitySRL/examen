package ar.com.plug.examen.business.domain;

public class FacturaCompraItems {

	private long idProducto;
	private long nombreProducto;
	private int cantidad;
	private double precio;
	private double subtotal;

	public FacturaCompraItems() {

	}

	public FacturaCompraItems(long idProducto, long nombreProducto, int cantidad, double precio, double subtotal) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(long nombreProducto) {
		this.nombreProducto = nombreProducto;
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

	@Override
	public String toString() {
		return "CompraItems [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad
				+ "]";
	}
}
