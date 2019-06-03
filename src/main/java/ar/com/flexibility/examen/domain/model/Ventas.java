package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.com.flexibility.examen.app.rest.VentasController;

//Consideré que se refiere a las transacciones de ventas de una empresa. ya que si se referieran a las transacciones de compra el ABM realizado anteriormente deberia se de proveedores y no clientes.

@Entity
public class Ventas {
	private static final Log logger = LogFactory.getLog(Ventas.class);
	@Id
	@Column(name="idDeVenta")
	private int idDeVenta;
	@JoinColumn(name="cliente")
	@ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private Cliente cliente;
	@JoinColumn(name="producto")
	@ManyToOne(targetEntity = Producto.class, fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private Producto producto;
	@Column(name="cantidad")
	private Integer cantidad;
	@Column(name="precioTotal")
	private float precioTotal;
	@Column(name="demoraEntrega")
	private int demoraEntrega;
	@Column(name="plazoDePago")
	private int plazoDePago;
	@Column(name="clausulaDolar")
	private boolean clausulaDolar;
	@Column(name="aprobacion")
	private Aprobacion aprobacion;
	
	public int getIdDeVenta() {
		return idDeVenta;
	}
	public void setIdDeVenta(int idDeVenta) {
		this.idDeVenta = idDeVenta;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float cantidad, Producto producto) {
		this.precioTotal = producto.getPrecio()*this.cantidad;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getDemoraEntrega() {
		return demoraEntrega;
	}
	public void setDemoraEntrega(int demoraEntrega) {
		this.demoraEntrega = demoraEntrega;
	}
	public int getPlazoDePago() {
		return plazoDePago;
	}
	public void setPlazoDePago(int plazoDePago) {
		this.plazoDePago = plazoDePago;
	}
	public boolean isClausulaDolar() {
		return clausulaDolar;
	}
	public void setClausulaDolar(boolean clausulaDolar) {
		this.clausulaDolar = clausulaDolar;
	}
	public Aprobacion getAprobacion() {
		return aprobacion;
	}
	public void setAprobacion(Aprobacion aprobacion) {
		this.aprobacion = aprobacion;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public void aprobarVenta() {
		try{ 
			this.aprobacion = this.aprobacion.aprobarVenta();
			logger.info("venta Aprobada");
		}catch(Exception e) {
			logger.info("Error al aprobar la venta");
		}
	}
	public void desaprobarVenta() {
		try {
		this.aprobacion = this.aprobacion.desaprobarVenta();
		logger.info("venta Desaprobada");
		}catch(Exception e) {
			logger.info("Error al desaprobar la venta");
		}
	}
}
