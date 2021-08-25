package ar.com.plug.examen.domain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FacturaDTO {
	
	private long idFactura;
	private long idCliente;
	private long idVendedor;
	private String codigo;
	private Date fecha;
	private BigDecimal total;
	private boolean estado;
	private List<FacturaDetalleDTO> facturaDetalle;
	public FacturaDTO() {
		
	}
	public FacturaDTO(long idFactura, long idCliente, long idVendedor, String codigo, Date fecha, BigDecimal total,
			boolean estado, List<FacturaDetalleDTO> facturaDetalle) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.facturaDetalle = facturaDetalle;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public List<FacturaDetalleDTO> getFacturaDetalle() {
		return facturaDetalle;
	}
	public void setFacturaDetalle(List<FacturaDetalleDTO> facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}
	@Override
	public String toString() {
		return "FacturaDTO [idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVendedor=" + idVendedor
				+ ", codigo=" + codigo + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado
				+ ", facturaDetalle=" + facturaDetalle + "]";
	}
}
