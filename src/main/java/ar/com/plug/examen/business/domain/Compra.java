package ar.com.plug.examen.business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
	
	private long idFactura;
	private long idCliente;
	private long idVendedor;
	private String codigo;
	private Date fecha; 
	private Double totalFactura;
	private List<CompraDetalle> compraDetalle = new  ArrayList<CompraDetalle>();
		
	public Compra() {
	
	}

	public Compra(long idFactura, long idCliente, long idVendedor, String codigo, Date fecha, Double totalFactura,
			List<CompraDetalle> compraDetalle) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.codigo = codigo;
		this.fecha = fecha;
		this.totalFactura = totalFactura;
		this.compraDetalle = compraDetalle;
	}

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public List<CompraDetalle> getCompraDetalle() {
		return compraDetalle;
	}

	public void setCompraDetalle(List<CompraDetalle> compraDetalle) {
		this.compraDetalle = compraDetalle;
	}

	@Override
	public String toString() {
		return "Compra [idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVendedor=" + idVendedor
				+ ", codigo=" + codigo + ", fecha=" + fecha + ", totalFactura=" + totalFactura + ", compraDetalle="
				+ compraDetalle + "]";
	}
	
}
