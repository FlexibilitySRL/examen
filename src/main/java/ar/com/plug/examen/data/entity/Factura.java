package ar.com.plug.examen.data.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FACTURA")
public class Factura {	
	
	@Id
	@Column(name="ID_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFactura; 
	
	@Column(name="ID_CLIENTE")
	private Long idCliente; 
	
	@Column(name="ID_VENDEDOR")
	private Long idVendedor; 
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="TOTAL")
	private BigDecimal total;
	
	@Column(name="ESTADO")
	private boolean estado;
	
	@OneToMany(mappedBy="factura",targetEntity = FacturaDetalle.class)
	@JsonIgnoreProperties("factura")
    private List<FacturaDetalle> facturaDetalle;
		
	public Factura() {
			
	}	
		
	public Factura(long idFactura, Long idCliente, Long idVendedor, String codigo, Date fecha, BigDecimal total,
			boolean estado, List<FacturaDetalle> facturaDetalle) {
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
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

	public List<FacturaDetalle> getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(List<FacturaDetalle> facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVendedor=" + idVendedor
				+ ", codigo=" + codigo + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + "]";
	}
	
}
