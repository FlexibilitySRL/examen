package ar.com.plug.examen.business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaCompra {
	
	private long idFactura;
	private long idCliente;
	private String nombreCliente;
	private String apellidoCliente;
	private String numeroIdentificacion;
	private long idVendedor;
	private String nombreVendedor;
	private String apellidoVendedor;	
	private Date fecha; 
	private String codigo;
	private Double totalFactura;
	private List<CompraDetalle> compraDetalle = new  ArrayList<CompraDetalle>();
	
	public FacturaCompra() {
		
	}

	public FacturaCompra(long idFactura, long idCliente, String nombreCliente, String apellidoCliente, long idVendedor,
			String nombreVendedor, String apellidoVendedor, Date fecha, String codigo, Double totalFactura,
			List<CompraDetalle> compraDetalle) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.idVendedor = idVendedor;
		this.nombreVendedor = nombreVendedor;
		this.apellidoVendedor = apellidoVendedor;
		this.fecha = fecha;
		this.codigo = codigo;
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

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}
	
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getNombreVendedor() {
		return nombreVendedor;
	}

	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}

	public String getApellidoVendedor() {
		return apellidoVendedor;
	}

	public void setApellidoVendedor(String apellidoVendedor) {
		this.apellidoVendedor = apellidoVendedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		return "FacturaCompra [idFactura=" + idFactura + ", idCliente=" + idCliente + ", nombreCliente=" + nombreCliente
				+ ", apellidoCliente=" + apellidoCliente + ", idVendedor=" + idVendedor + ", nombreVendedor="
				+ nombreVendedor + ", apellidoVendedor=" + apellidoVendedor + ", fecha=" + fecha + ", codigo=" + codigo
				+ ", totalFactura=" + totalFactura + ", compraDetalle=" + compraDetalle + "]";
	}
	
	
	
}
