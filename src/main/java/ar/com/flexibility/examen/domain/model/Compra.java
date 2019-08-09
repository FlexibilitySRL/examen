package ar.com.flexibility.examen.domain.model;

import org.joda.time.DateTime;

import ar.com.flexibility.examen.domain.model.Producto;

public class Compra {

	private Producto producto;
	private DateTime fecha;
	private long monto;
	
	public Compra(Producto producto, DateTime fecha, long monto) {
		this.setProducto(producto);
		this.setFecha(fecha);
		this.setMonto(monto);
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public DateTime getFecha() {
		return fecha;
	}

	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}

	public long getMonto() {
		return monto;
	}

	public void setMonto(long monto) {
		this.monto = monto;
	}
}
